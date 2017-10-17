package com.apress.springrecipes.board.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
                .dataSource(dataSource())
                .usersByUsernameQuery("SELECT username, password, 'true' as enabled FROM member WHERE username = ?")
                .authoritiesByUsernameQuery(
                        "SELECT member.username, member_role.role as authorities " +
                                "FROM member, member_role " +
                                "WHERE  member.username = ? AND member.id = member_role.member_id");
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
