package com.apress.springrecipes.reactive.court;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapUserDetailsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    SecurityWebFilterChain springWebFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeExchange()
                    .pathMatchers("/welcome", "/welcome/**").permitAll()
                    .pathMatchers("/reservation*").hasRole("USER")
                    .pathMatchers("/users/{user}/**").access(this::currentUserMatchesPath)
                    .anyExchange().authenticated()
                .and()
                .build();
    }

    private Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication, AuthorizationContext context) {
        return authentication
                .map( a -> context.getVariables().get("user").equals(a.getName()))
                .map( granted -> new AuthorizationDecision(granted));
    }


    @Bean
    public MapUserDetailsRepository userDetailsRepository() {
        UserDetails rob = User.withUsername("marten").password("secret").roles("USER").build();
        UserDetails admin = User.withUsername("admin").password("admin").roles("USER","ADMIN").build();
        return new MapUserDetailsRepository(rob, admin);
    }

}
