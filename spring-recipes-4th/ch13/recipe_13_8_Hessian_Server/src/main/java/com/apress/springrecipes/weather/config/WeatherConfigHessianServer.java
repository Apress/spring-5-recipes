package com.apress.springrecipes.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

import com.apress.springrecipes.weather.WeatherService;
import com.apress.springrecipes.weather.WeatherServiceImpl;

@Configuration
public class WeatherConfigHessianServer {

    @Bean
    public WeatherService weatherService() {
        WeatherService wService = new WeatherServiceImpl();
        return wService;
    }

    @Bean(name = "/weather")
    public HessianServiceExporter exporter() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(weatherService());
        exporter.setServiceInterface(WeatherService.class);
        return exporter;
    }
}
