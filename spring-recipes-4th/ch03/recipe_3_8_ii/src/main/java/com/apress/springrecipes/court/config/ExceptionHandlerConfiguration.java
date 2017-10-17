package com.apress.springrecipes.court.config;

import com.apress.springrecipes.court.service.ReservationNotAvailableException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.List;
import java.util.Properties;

@Configuration
public class ExceptionHandlerConfiguration implements WebMvcConfigurer {

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(handlerExceptionResolver());
    }

    @Bean
    public SimpleMappingExceptionResolver handlerExceptionResolver() {
        Properties exceptionMapping = new Properties();
        exceptionMapping.setProperty(ReservationNotAvailableException.class.getName(), "reservationNotAvailable");

        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        exceptionResolver.setExceptionMappings(exceptionMapping);
        exceptionResolver.setDefaultErrorView("error");
        return exceptionResolver;
    }
}
