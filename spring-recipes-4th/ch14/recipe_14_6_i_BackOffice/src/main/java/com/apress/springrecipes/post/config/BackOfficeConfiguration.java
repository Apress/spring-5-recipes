package com.apress.springrecipes.post.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.springrecipes.post.BackOffice;
import com.apress.springrecipes.post.BackOfficeImpl;

/**
 * Created by marten on 02-06-14.
 */
@Configuration
public class BackOfficeConfiguration {

    @Bean
    public BackOffice backOffice() {
        return new BackOfficeImpl();
    }

}
