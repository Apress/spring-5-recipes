package com.apress.springrecipes.court.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by marten on 16-06-14.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.apress.springrecipes.court")
public class CourtRestConfiguration {}
