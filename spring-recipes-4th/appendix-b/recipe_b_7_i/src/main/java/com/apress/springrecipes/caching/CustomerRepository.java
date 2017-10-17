package com.apress.springrecipes.caching;

/**
 * Created by marten on 18-08-14.
 */
public interface CustomerRepository {

    Customer find(long customerId);
    Customer create(String name);
    void update(Customer customer);
    void remove(long customerId);
}
