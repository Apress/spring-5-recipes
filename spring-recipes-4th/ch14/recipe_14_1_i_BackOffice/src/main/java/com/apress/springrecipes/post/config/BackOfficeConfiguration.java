package com.apress.springrecipes.post.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.springrecipes.post.BackOfficeImpl;

/**
 * Created by marten on 02-06-14.
 */
@Configuration
public class BackOfficeConfiguration {

    @Bean
    public BackOfficeImpl backOffice() {
        return new BackOfficeImpl();
    }
}
