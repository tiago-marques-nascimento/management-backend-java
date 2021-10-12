package com.users.management.infrastructure.configuration.jwt;

import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import com.users.management.infrastructure.configuration.environment.EnvironmentParameters;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final EnvironmentParameters environmentParameters;

    public JwtAuthorizationFilter(final AuthenticationManager authManager, final EnvironmentParameters environmentParameters) {
        super(authManager);
        this.environmentParameters = environmentParameters;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(this.environmentParameters.getHeaderString());

        if (header == null || !header.startsWith(this.environmentParameters.getTokenPrefix())) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        if (authentication == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(final HttpServletRequest request) {
        String token = request.getHeader(this.environmentParameters.getHeaderString());

        if (token != null) {

            try {

                DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(this.environmentParameters.getSecret().getBytes()))
                        .build()
                        .verify(token.replace(this.environmentParameters.getTokenPrefix(), ""));
                String user = decodedJWT.getSubject();
                List<JwtGrantedAuthority> claims = decodedJWT.getClaim("claims").asList(String.class)
                    .stream()
                    .map(claim -> new JwtGrantedAuthority(claim))
                    .collect(Collectors.toList());

                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, claims);
                }
                return null;

            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
