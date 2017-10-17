package com.apress.springrecipes.springintegration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class Main {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(IntegrationConfiguration.class);

        Map<String, Object> customerMap = new HashMap<>();
        customerMap.put("id", 123L);
        customerMap.put("firstName", "Marten");
        customerMap.put("lastName", "Deinum");

        JmsTemplate jms = context.getBean(JmsTemplate.class);
        jms.convertAndSend("recipe-15-5", customerMap);

        System.out.println("Press [ENTER] to stop.");
        System.in.read();

        context.close();
    }
}
