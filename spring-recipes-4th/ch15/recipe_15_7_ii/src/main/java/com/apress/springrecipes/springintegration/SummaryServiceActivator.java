package com.apress.springrecipes.springintegration;

import java.util.Collection;

import org.springframework.integration.annotation.ServiceActivator;

public class SummaryServiceActivator {

    @ServiceActivator
    public void summary(Collection<Customer> customers) {
        System.out.printf("Removed %s customers.%n", customers.size());
    }
}
