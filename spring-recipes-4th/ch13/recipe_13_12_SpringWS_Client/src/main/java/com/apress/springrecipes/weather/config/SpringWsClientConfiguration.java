package com.apress.springrecipes.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.apress.springrecipes.weather.WeatherService;
import com.apress.springrecipes.weather.WeatherServiceClient;
import com.apress.springrecipes.weather.WeatherServiceProxy;

@Configuration
public class SpringWsClientConfiguration {

    @Bean
    public WeatherServiceClient weatherServiceClient(WeatherService weatherService) throws Exception {
        return new WeatherServiceClient(weatherService);
    }

    @Bean
    public WeatherServiceProxy weatherServiceProxy(WebServiceTemplate webServiceTemplate) throws Exception {
        return new WeatherServiceProxy(webServiceTemplate);
    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller());
        webServiceTemplate.setDefaultUri("http://localhost:8080/springws/services");
        return webServiceTemplate;
    }

    @Bean
    public CastorMarshaller marshaller() {
        CastorMarshaller marshaller = new CastorMarshaller();
        marshaller.setMappingLocation(new ClassPathResource("/mapping.xml"));
        return marshaller;
    }
}
