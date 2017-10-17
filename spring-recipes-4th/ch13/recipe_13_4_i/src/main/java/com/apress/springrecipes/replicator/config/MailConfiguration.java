package com.apress.springrecipes.replicator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.springrecipes.replicator.EmailErrorNotifier;
import com.apress.springrecipes.replicator.ErrorNotifier;

/**
 * Created by marten on 26-05-14.
 */
@Configuration
public class MailConfiguration {

    @Bean
    public ErrorNotifier errorNotifier() {
        return new EmailErrorNotifier();
    }
}
