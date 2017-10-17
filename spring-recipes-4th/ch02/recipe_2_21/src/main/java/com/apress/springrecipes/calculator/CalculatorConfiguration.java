package com.apress.springrecipes.calculator;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by marten on 24-01-17.
 */
@Configuration
@ComponentScan
public class CalculatorConfiguration {

    @Bean
    public ComplexCachingAspect complexCachingAspect() {

        Map<String, Complex> cache = new HashMap<>();
        cache.put("2,3", new Complex(2,3));
        cache.put("3,5", new Complex(3,5));

        ComplexCachingAspect complexCachingAspect = Aspects.aspectOf(ComplexCachingAspect.class);
        complexCachingAspect.setCache(cache);
        return complexCachingAspect;
    }
}
