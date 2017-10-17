package com.apress.springrecipes.court.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@Configuration
public class ViewResolverConfiguration {

    @Bean
    public ResourceBundleViewResolver viewResolver() {

        ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setBasename("court-views");
        return viewResolver;
    }
}
