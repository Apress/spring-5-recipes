package com.apress.springrecipes.springintegration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;


public class ClientMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ClientConfiguration.class);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        Map<String, Object> customer = new HashMap<>();
        customer.put("id", 1234L);
        customer.put("firstName", "Marten");
        customer.put("lastName", "Deinum");

        jmsTemplate.convertAndSend("recipe-15-6", customer);

        Map<String, Object> customer2 = new HashMap<>();
        customer2.put("id", 666L);
        customer2.put("firstName", "Foo");
        customer2.put("lastName", "Bar");

        jmsTemplate.convertAndSend("recipe-15-6", customer2);

        context.close();

    }
}
