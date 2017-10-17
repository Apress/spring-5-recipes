package com.apress.springrecipes.springintegration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;


public class Main {
    public static void main(String[] args)  {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(IntegrationConfiguration.class);

        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

        Map<String, Object> customer = new HashMap<>();
        customer.put("id", 1234L);
        customer.put("firstName", "Marten");
        customer.put("lastName", "Deinum");

        jmsTemplate.convertAndSend("recipe-15-2", customer);

        applicationContext.close();

    }
}
