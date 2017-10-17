package com.apress.springrecipes.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Created by marten on 26-05-14.
 */
@Configuration
@EnableWs
@ComponentScan("com.apress.springrecipes.weather")
public class SpringWsConfiguration {

    @Bean
    public DefaultWsdl11Definition temperature() {
        DefaultWsdl11Definition temperature = new DefaultWsdl11Definition();
        temperature.setPortTypeName("Weather");
        temperature.setLocationUri("/");
        temperature.setSchema(temperatureSchema());
        return temperature;
    }

    @Bean
    public XsdSchema temperatureSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/META-INF/xsd/temperature.xsd"));
    }
}
