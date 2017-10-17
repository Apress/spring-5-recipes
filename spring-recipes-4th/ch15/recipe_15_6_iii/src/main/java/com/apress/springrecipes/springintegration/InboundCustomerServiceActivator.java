package com.apress.springrecipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;


public class InboundCustomerServiceActivator {
    private static final Logger logger = LoggerFactory.getLogger(InboundCustomerServiceActivator.class);

    @ServiceActivator
    public void doSomethingWithCustomer(Customer customer) {
        logger.debug("id={}, firstName: {}, lastName: {}", customer.getId(), customer.getFirstName(), customer.getLastName());
    }
}
