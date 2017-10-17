package com.apress.springrecipes.springintegration;

import java.util.Map;

import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

public class InboundJMSMessageToCustomerTransformer {

    @Transformer
    public Message<Customer> transformJMSMapToCustomer(
            Message<Map<String, Object>> inboundSprignIntegrationMessage) {

        Map<String, Object> jmsMessagePayload = inboundSprignIntegrationMessage.getPayload();
        Customer customer = new Customer();
        customer.setFirstName((String) jmsMessagePayload.get("firstName"));
        customer.setLastName((String) jmsMessagePayload.get("lastName"));
        customer.setId((Long) jmsMessagePayload.get("id"));

        return MessageBuilder.withPayload(customer)
                .copyHeadersIfAbsent(inboundSprignIntegrationMessage.getHeaders())
                .setHeaderIfAbsent("randomlySelectedForSurvey", Math.random() > .5)
                .build();
    }
}
