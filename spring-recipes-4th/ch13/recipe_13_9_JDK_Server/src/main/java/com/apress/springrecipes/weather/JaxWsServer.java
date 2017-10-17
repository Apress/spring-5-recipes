package com.apress.springrecipes.weather;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.weather.config.WeatherConfigJaxWsServer;

public class JaxWsServer {

    public static void main(String[] args) throws IOException {
        new AnnotationConfigApplicationContext(WeatherConfigJaxWsServer.class);
    }
}
