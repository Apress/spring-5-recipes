package com.apress.springrecipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;


public class InboundCustomerServiceActivator {
    private static final Logger logger = LoggerFactory.getLogger(InboundCustomerServiceActivator.class);

    @ServiceActivator
    public void doSomethingWithCustomer(Message<Customer> customerMessage) {
        Customer customer = customerMessage.getPayload();
        logger.debug("id={}, firstName: {}, lastName: {}", customer.getId(), customer.getFirstName(), customer.getLastName());
    }
}
