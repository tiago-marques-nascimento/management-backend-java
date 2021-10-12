package com.users.management.infrastructure.configuration.jwt;

import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import java.util.Optional;
import javax.inject.Inject;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.ClassPath;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.users.management.domain.service.UserServiceInterface;
import com.users.management.domain.entity.User;
import com.users.management.infrastructure.configuration.environment.EnvironmentParameters;

@Configuration
class JwtConfiguration extends WebSecurityConfigurerAdapter {

    @Inject
    private UserServiceInterface userService;

    @Inject
    private EnvironmentParameters environmentParameters;

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry = http.cors().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests();

        for (ClassPath.ClassInfo classInfo : ClassPath.from(JwtConfiguration.class.getClassLoader()).getTopLevelClasses("com.users.management.application.controller")) {
            Class controller = JwtConfiguration.class.getClassLoader().loadClass(classInfo.getName());
            String endpointPrefix = "";
            for (Annotation annotation : controller.getAnnotations()) {
                if (annotation.annotationType().getName().equals("org.springframework.web.bind.annotation.RequestMapping")) {
                    endpointPrefix = ((String[]) annotation.annotationType().getMethod("value").invoke(annotation))[0].toString();
                }
            }
            for (Method method : controller.getMethods()) {
                String[] roles = null;
                RequestMethod requestMethod = null;
                String endpoint = null;
                for (Annotation annotation : method.getAnnotations()) {
                    if (annotation.annotationType().getName().equals("com.users.management.infrastructure.configuration.jwt.WithAnyOfTheseRoles")) {
                        roles = (String[]) (annotation.annotationType().getMethod("roles").invoke(annotation));
                    } else if (annotation.annotationType().getName().equals("org.springframework.web.bind.annotation.RequestMapping")) {
                        requestMethod = ((RequestMethod[]) (annotation.annotationType().getMethod("method").invoke(annotation)))[0];
                        endpoint = ((String[]) (annotation.annotationType().getMethod("value").invoke(annotation)))[0];
                    }
                }

                if (roles != null && requestMethod != null && endpointPrefix != null && endpoint != null) {
                    HttpMethod httpMethod;
                    if (RequestMethod.GET.equals(requestMethod)) {
                        httpMethod = HttpMethod.GET;
                    } else if (RequestMethod.POST.equals(requestMethod)) {
                        httpMethod = HttpMethod.POST;
                    } else if (RequestMethod.PUT.equals(requestMethod)) {
                        httpMethod = HttpMethod.PUT;
                    } else if (RequestMethod.DELETE.equals(requestMethod)) {
                        httpMethod = HttpMethod.DELETE;
                    } else {
                        httpMethod = HttpMethod.GET;
                    }
                    expressionInterceptUrlRegistry = ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) expressionInterceptUrlRegistry
                        .antMatchers(httpMethod, endpointPrefix + endpoint))
                        .hasAnyAuthority(roles);
                }
            }
        }
        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) expressionInterceptUrlRegistry.anyRequest())
            .permitAll()
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationManager(), environmentParameters))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), environmentParameters));
    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        UserServiceInterface localUserService = this.userService;
        auth.userDetailsService(new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

                Optional<User> user = localUserService.findByNameWithRoles(username);
                if (!user.isPresent()) {
                    throw new UsernameNotFoundException("User not found");
                }
                return new JwtUserDetails(user.get());
            }
        });
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
