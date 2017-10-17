package com.apress.springrecipes.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import com.apress.springrecipes.weather.WeatherService;
import com.apress.springrecipes.weather.WeatherServiceImpl;

@Configuration
public class WeatherConfigServer {

    @Bean
    public WeatherService weatherService() {
        return new WeatherServiceImpl();
    }

    @Bean
    public RmiServiceExporter rmiService() {
        RmiServiceExporter rmiService = new RmiServiceExporter();
        rmiService.setServiceName("WeatherService");
        rmiService.setServiceInterface(com.apress.springrecipes.weather.WeatherService.class);
        rmiService.setService(weatherService());
        return rmiService;
    }

}
