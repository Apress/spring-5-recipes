package com.apress.springrecipes.weather.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import com.apress.springrecipes.weather.WeatherService;
import com.apress.springrecipes.weather.WeatherServiceClient;

@Configuration
public class WeatherConfigJaxWsClient {

    @Bean
    @DependsOn("weatherService")
    public WeatherServiceClient weatherClient() {
        WeatherServiceClient wServiceClient = new WeatherServiceClient();
        return wServiceClient;
    }

    @Bean
    public JaxWsPortProxyFactoryBean weatherService() throws MalformedURLException {
        JaxWsPortProxyFactoryBean weatherService = new JaxWsPortProxyFactoryBean();
        weatherService.setServiceInterface(WeatherService.class);
        weatherService.setWsdlDocumentUrl(new URL("http://localhost:8888/jaxws/weather?WSDL"));
        weatherService.setNamespaceUri("http://weather.springrecipes.apress.com/");
        weatherService.setServiceName("weather");
        weatherService.setPortName("WeatherServiceImplPort");
        return weatherService;
    }
}
