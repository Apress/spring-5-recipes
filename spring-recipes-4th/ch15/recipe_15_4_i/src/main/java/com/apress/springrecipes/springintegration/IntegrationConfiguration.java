package com.apress.springrecipes.springintegration;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;

@Configuration
@EnableIntegration
@ComponentScan
public class IntegrationConfiguration {


    @Bean
    public InboundHelloWorldFileMessageProcessor messageProcessor() {
        return new InboundHelloWorldFileMessageProcessor();
    }

    @Bean
    public IntegrationFlow inboundFileFlow(@Value("${user.home}/inboundFiles/new/") File directory) {
        return IntegrationFlows
                .from(
                        Files.inboundAdapter(directory).patternFilter("*.csv"),
                        c -> c.poller(Pollers.fixedRate(10, TimeUnit.SECONDS)))
                .handle(messageProcessor())
                .get();
    }
}
