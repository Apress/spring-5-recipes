package com.apress.springrecipes.weather.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.adapter.method.MarshallingPayloadMethodProcessor;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
@ComponentScan("com.apress.springrecipes.weather")
public class SpringWsConfiguration extends WsConfigurerAdapter {

    @Bean
    public MarshallingPayloadMethodProcessor marshallingPayloadMethodProcessor() {
        return new MarshallingPayloadMethodProcessor(marshaller());
    }

    @Bean
    public Marshaller marshaller() {
        CastorMarshaller marshaller = new CastorMarshaller();
        marshaller.setMappingLocation(new ClassPathResource("/mapping.xml"));
        return marshaller;
    }

    @Bean
    public DefaultWsdl11Definition temperature() {
        DefaultWsdl11Definition temperature = new DefaultWsdl11Definition();
        temperature.setPortTypeName("Weather");
        temperature.setLocationUri("/");
        temperature.setSchema(temperatureSchema());
        return temperature;
    }

    @Bean
    public XsdSchema temperatureSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/META-INF/xsd/temperature.xsd"));
    }


    @Override
    public void addArgumentResolvers(List<MethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(marshallingPayloadMethodProcessor());
    }

    @Override
    public void addReturnValueHandlers(List<MethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(marshallingPayloadMethodProcessor());
    }
}
