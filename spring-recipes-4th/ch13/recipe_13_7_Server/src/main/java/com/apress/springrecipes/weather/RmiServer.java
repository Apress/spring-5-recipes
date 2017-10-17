package com.apress.springrecipes.weather;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.weather.config.WeatherConfigServer;

public class RmiServer {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(WeatherConfigServer.class);
    }
}
