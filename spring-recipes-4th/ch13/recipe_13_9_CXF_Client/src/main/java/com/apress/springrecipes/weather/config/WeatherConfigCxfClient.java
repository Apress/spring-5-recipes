package com.apress.springrecipes.weather.config;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.apress.springrecipes.weather.WeatherService;
import com.apress.springrecipes.weather.WeatherServiceClient;

@Configuration
@ImportResource("classpath:META-INF/cxf/cxf.xml")
public class WeatherConfigCxfClient {

    @Bean
    public WeatherServiceClient weatherClient(WeatherService weatherService) {
        return new WeatherServiceClient(weatherService);
    }

    @Bean
    public WeatherService weatherServiceProxy() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(WeatherService.class);
        factory.setAddress("http://localhost:8080/cxf/weather");
        return (WeatherService) factory.create();
    }
}
