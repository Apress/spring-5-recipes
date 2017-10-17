package com.apress.springrecipes.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.apress.springrecipes.weather.WeatherService;
import com.apress.springrecipes.weather.WeatherServiceClient;

@Configuration
public class WeatherConfigHessianClient {

    @Bean
    public HessianProxyFactoryBean weatherService() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl("http://localhost:8080/hessian/weather");
        factory.setServiceInterface(WeatherService.class);
        return factory;
    }

    @Bean
    public WeatherServiceClient weatherClient() {
        return new WeatherServiceClient();
    }

}
