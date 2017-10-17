package com.apress.springrecipes.springintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
@EnableIntegration
public class AdditionConfiguration {

    @Bean
    public AdditionService additionService() {
        return new AdditionService();
    }

    @Bean
    public IntegrationFlow additionFlow() {
        return IntegrationFlows.
                from("request")
                .handle(additionService(), "add")
                .channel("response").get();
    }

}

