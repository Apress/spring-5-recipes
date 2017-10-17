package com.apress.springrecipes.board.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class TodoSecurityConfig extends WebSecurityConfigurerAdapter {

    public TodoSecurityConfig() {
        super(true);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("user").authorities("USER")
                .and()
                .withUser("admin").password("admin").authorities("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/todos*").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/todos*").hasAuthority("ADMIN")
            .and()
                .anonymous()
            .and()
                .servletApi()
            .and()
                .securityContext()
            .and()
                .exceptionHandling()
            .and()
                .rememberMe()
            .and()
                .formLogin()
                    .loginPage("/login.jsp")
                    .loginProcessingUrl("/login")
                    .failureUrl("/login.jsp?error=true")
                    .defaultSuccessUrl("/todos")
                .permitAll()
            .and()
                .logout().logoutSuccessUrl("/logout-success.jsp")
            .and()
                .headers()
            .and()
                .csrf();
    }
}
