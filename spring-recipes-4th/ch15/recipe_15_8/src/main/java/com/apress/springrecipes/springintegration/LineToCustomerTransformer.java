package com.apress.springrecipes.springintegration;

import org.springframework.integration.annotation.Transformer;

public class LineToCustomerTransformer {

    @Transformer
    public Customer transfor(String line) {
        String[] columns = line.split(",");
        return new Customer(Long.valueOf(columns[0]), columns[1], columns[2], Long.valueOf(columns[3]));
    }
}
