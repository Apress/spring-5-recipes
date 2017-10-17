package com.apress.springrecipes.court.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * Configuration class to trigger scanning for all {@code @Component} annotated classes and to enable Spring MVC
 * processing with the {@link EnableWebMvc} annotation. Further modification is done through smaller additional
 * configuration files in the different recipes.
 *
 * @author Marten Deinum
 * @since 4.0.0
 */
@Configuration
@ComponentScan("com.apress.springrecipes.court")
@EnableWebMvc
public class CourtConfiguration {

}
