package com.apress.springrecipes.replicator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;

/**
 * Created by marten on 21-05-14.
 */
@Configuration
public class JmxConfig {

    @Bean
    public MBeanExporter mbeanExporter() {
        AnnotationMBeanExporter mbeanExporter = new AnnotationMBeanExporter();
        mbeanExporter.setDefaultDomain("bean");
        return mbeanExporter;
    }
}

