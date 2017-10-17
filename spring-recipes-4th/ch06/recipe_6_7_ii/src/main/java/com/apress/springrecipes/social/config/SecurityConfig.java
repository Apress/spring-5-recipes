package com.apress.springrecipes.social.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import com.apress.springrecipes.social.security.SimpleSocialUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .antMatchers("/signup").permitAll()
                .anyRequest().authenticated().and()
            .httpBasic().disable()
            .formLogin()
                .loginPage("/signin")
                .failureUrl("/signin?param.error=bad_credentials")
                .loginProcessingUrl("/signin/authenticate").permitAll()
            .and()
                .logout().logoutUrl("/signout").permitAll();

        http.apply(new SpringSocialConfigurer());

    }

    @Bean
    public SocialUserDetailsService socialUserDetailsService(UserDetailsService userDetailsService) {
        return new SimpleSocialUserDetailsService(userDetailsService);
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
        userDetailsManager.setDataSource(dataSource);
        userDetailsManager.setEnableAuthorities(true);
        return userDetailsManager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsManager(null));
    }
}
