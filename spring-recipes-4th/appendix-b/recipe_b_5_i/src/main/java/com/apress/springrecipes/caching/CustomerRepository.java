package com.apress.springrecipes.caching;

public interface CustomerRepository {

    Customer find(long customerId);
    Customer create(String name);
    void update(Customer customer);
    void remove(long customerId);
}
