package com.apress.springrecipes.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.apress.springrecipes.weather.WeatherService;
import com.apress.springrecipes.weather.WeatherServiceImpl;

@Configuration
public class WeatherConfigHTTPInvokerServer {

    @Bean
    public WeatherService weatherService() {
        WeatherService wService = new WeatherServiceImpl();
        return wService;
    }

    @Bean(name = "/weather")
    public HttpInvokerServiceExporter exporter() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(weatherService());
        exporter.setServiceInterface(WeatherService.class);
        return exporter;
    }

}
