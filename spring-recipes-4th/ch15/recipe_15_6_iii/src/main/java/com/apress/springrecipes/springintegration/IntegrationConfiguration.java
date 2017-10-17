package com.apress.springrecipes.springintegration;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.integration.router.ErrorMessageExceptionTypeRouter;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.messaging.MessageHandlingException;

@Configuration
@EnableIntegration
@ComponentScan
public class IntegrationConfiguration {

    @Bean
    public CachingConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean
    public InboundJMSMessageToCustomerTransformer customerTransformer() {
        return new InboundJMSMessageToCustomerTransformer();
    }

    @Bean
    public InboundCustomerServiceActivator customerServiceActivator() {
        return new InboundCustomerServiceActivator();
    }

    @Bean
    public DefaultErrorHandlingServiceActivator errorHandlingServiceActivator() {
        return new DefaultErrorHandlingServiceActivator();
    }

    @Bean
    public ErrorMessageExceptionTypeRouter exceptionTypeRouter() {
        ErrorMessageExceptionTypeRouter router = new ErrorMessageExceptionTypeRouter();
        router.setChannelMapping(MyCustomException.class.getName(), "customExceptionChannel");
        router.setChannelMapping(RuntimeException.class.getName(), "runtimeExceptionChannel");
        router.setChannelMapping(MessageHandlingException.class.getName(), "messageHandlingExceptionChannel");
        return router;
    }

    @Bean
    public IntegrationFlow errorFlow() {
        return IntegrationFlows
                    .from("errorChannel")
                    .route(exceptionTypeRouter())
                .get();
    }

    @Bean
    public IntegrationFlow customExceptionFlow() {
        return IntegrationFlows
                    .from("customExceptionChannel")
                    .handle(errorHandlingServiceActivator())
                .get();
    }

    @Bean
    public IntegrationFlow jmsInbound(ConnectionFactory connectionFactory) {
        return IntegrationFlows
                .from(Jms.messageDrivenChannelAdapter(connectionFactory).extractPayload(true).destination("recipe-15-6")
                        .errorChannel("errorChannel").extractPayload(true))
                .transform(customerTransformer())
                .handle(customerServiceActivator())
                .get();
    }
}
