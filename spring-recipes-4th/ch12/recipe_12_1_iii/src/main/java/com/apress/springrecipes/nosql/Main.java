package com.apress.springrecipes.nosql;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.apress.springrecipes.nosql.config.MongoConfiguration;

/**
 * Created by marten on 22-09-14.
 */
public class Main {

    public static final String DB_NAME = "vehicledb";

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfiguration.class);
        VehicleRepository repository = ctx.getBean(VehicleRepository.class);

        System.out.println("Number of Vehicles: " + repository.count());

        repository.save(new Vehicle("TEM0001", "RED", 4, 4));
        repository.save(new Vehicle("TEM0002", "RED", 4, 4));

        System.out.println("Number of Vehicles: " + repository.count());

        Vehicle v = repository.findByVehicleNo("TEM0001");

        System.out.println(v);

        List<Vehicle> vehicleList = repository.findAll();

        System.out.println("Number of Vehicles: " + vehicleList.size());

        vehicleList.forEach(System.out::println);

        System.out.println("Number of Vehicles: " + repository.count());

        ((AbstractApplicationContext) ctx).close();

    }
}
