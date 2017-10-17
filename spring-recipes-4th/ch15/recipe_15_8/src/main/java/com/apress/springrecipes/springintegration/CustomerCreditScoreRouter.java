package com.apress.springrecipes.springintegration;

import org.springframework.integration.annotation.Router;

public class CustomerCreditScoreRouter {

    @Router
    public String routeByCustomerCreditScore(Customer customer) {
        if (customer.getCreditScore() > 770) {
            return "safeCustomerChannel";
        } else {
            return "riskyCustomerChannel";
        }
    }
}
