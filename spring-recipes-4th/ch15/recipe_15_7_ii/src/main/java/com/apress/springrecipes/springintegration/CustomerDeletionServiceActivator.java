package com.apress.springrecipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;

public class CustomerDeletionServiceActivator {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ServiceActivator
    public Customer deleteCustomer(String customerId) {
        System.out.printf("the id of the customer to delete is %s%n", customerId);

        Customer customer = new Customer();
        customer.setId(Long.valueOf(customerId));
        return customer;
    }
}
