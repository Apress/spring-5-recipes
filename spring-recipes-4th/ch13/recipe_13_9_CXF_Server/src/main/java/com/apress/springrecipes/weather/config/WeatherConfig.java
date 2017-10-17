package com.apress.springrecipes.weather.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.apress.springrecipes.weather.WeatherService;
import com.apress.springrecipes.weather.WeatherServiceImpl;

@Configuration
@ImportResource("classpath:META-INF/cxf/cxf.xml")
public class WeatherConfig {

    @Bean
    public WeatherService weatherService() {
        return new WeatherServiceImpl();
    }

    @Bean(initMethod = "publish")
    public EndpointImpl endpoint(Bus bus) {
        EndpointImpl endpoint = new EndpointImpl(bus, weatherService());
        endpoint.setAddress("/weather");
        return endpoint;
    }
}
