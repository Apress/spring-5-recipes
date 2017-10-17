package com.apress.springrecipes.board.security;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.acls.AclEntryVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;

@Configuration
@EnableWebSecurity
public class TodoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("board")
                .addScript("classpath:/schema.sql")
                .addScript("classpath:/data.sql")
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
                .passwordEncoder(passwordEncoder())
                .dataSource(dataSource());
    }

    @Bean
    public AffirmativeBased accessDecisionManager(AclEntryVoter aclEntryVoter) {
        List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(new WebExpressionVoter(), aclEntryVoter);
        return new AffirmativeBased(decisionVoters);
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
