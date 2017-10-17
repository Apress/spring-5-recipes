package com.apress.springrecipes.board.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class TodoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .ldapAuthentication()
                .contextSource()
                    .ldif("")
                    .url("ldap://ldap-server:389/dc=springrecipes,dc=com")
                    .managerDn("cn=Directory Manager").managerPassword("ldap")
            .and()
                .userSearchFilter("uid={0}").userSearchBase("ou=people")
                .groupSearchFilter("member={0}").groupSearchBase("ou=groups")
                .passwordCompare()
                    .passwordEncoder(new LdapShaPasswordEncoder())
                    .passwordAttribute("userPassword");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/todos*").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/todos*").hasAuthority("ADMIN")
            .and()
                .httpBasic().disable()
                .formLogin()
                    .loginPage("/login.jsp")
                    .loginProcessingUrl("/login")
                    .failureUrl("/login.jsp?error=true")
                    .permitAll()
            .and()
                .logout().logoutSuccessUrl("/logout-success.jsp");
    }
}
