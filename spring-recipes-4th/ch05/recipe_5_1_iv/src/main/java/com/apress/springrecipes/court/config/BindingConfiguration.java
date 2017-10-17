package com.apress.springrecipes.court.config;

import com.apress.springrecipes.court.domain.SportTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by marten on 07-03-17.
 */
@Configuration
public class BindingConfiguration implements WebMvcConfigurer {

    @Autowired
    private SportTypeConverter sportTypeConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        System.out.println("Registring SportTypeConverter");
        registry.addConverter(sportTypeConverter);
    }

}
