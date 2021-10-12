package com.users.management.infrastructure.configuration.jwt;

import java.util.Date;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.management.application.viewmodel.UserViewModel;
import com.users.management.infrastructure.configuration.environment.EnvironmentParameters;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final EnvironmentParameters environmentParameters;

    public JwtAuthenticationFilter(final AuthenticationManager authenticationManager, final EnvironmentParameters environmentParameters) {
        this.authenticationManager = authenticationManager;
        this.environmentParameters = environmentParameters;

        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
                                                final HttpServletResponse response) throws AuthenticationException {

        try {

            UserViewModel user = new ObjectMapper().readValue(request.getInputStream(), UserViewModel.class);

            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    user.getName(),
                    user.getPassword())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request,
                                            final HttpServletResponse response,
                                            final FilterChain chain,
                                            final Authentication auth) throws IOException {

        String token = JWT.create()
                .withSubject(((JwtUserDetails) auth.getPrincipal()).getUsername())
                .withArrayClaim("claims", ((JwtUserDetails) auth.getPrincipal()).getAuthorities()
                    .stream()
                    .map(authority -> authority.getAuthority())
                    .collect(Collectors.toList()).toArray(new String[0]))
                .withExpiresAt(new Date(System.currentTimeMillis() + this.environmentParameters.getExpirationTime()))
                .sign(Algorithm.HMAC512(this.environmentParameters.getSecret().getBytes()));

        String body = "{\"data\": \"" + token + "\"}";

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(body);
        response.getWriter().flush();
    }
}
