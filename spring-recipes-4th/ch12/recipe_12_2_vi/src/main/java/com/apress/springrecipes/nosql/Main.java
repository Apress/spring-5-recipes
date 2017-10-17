package com.apress.springrecipes.nosql;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.apress.springrecipes.nosql.config.RedisConfig;

/**
 * Created by marten on 29-09-14.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(RedisConfig.class);

        @SuppressWarnings("unchecked")
        RedisTemplate<String, Vehicle> template = context.getBean(RedisTemplate.class);

        final String vehicleNo = "TEM0001";
        Vehicle vehicle = new Vehicle(vehicleNo, "RED", 4,4);
        template.opsForValue().set(vehicleNo, vehicle);
        System.out.println("Vehicle: " + template.opsForValue().get(vehicleNo));
    }
}
