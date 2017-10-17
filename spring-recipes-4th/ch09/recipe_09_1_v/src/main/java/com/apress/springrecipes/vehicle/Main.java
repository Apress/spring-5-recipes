package com.apress.springrecipes.vehicle;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.vehicle.config.VehicleConfiguration;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(VehicleConfiguration.class);

        VehicleDao vehicleDao = context.getBean(VehicleDao.class);
        Vehicle vehicle1 = new Vehicle("TEM0022", "Blue", 4, 4);
        Vehicle vehicle2 = new Vehicle("TEM0023", "Black", 4, 6);
        Vehicle vehicle3 = new Vehicle("TEM0024", "Green", 4, 5);
        vehicleDao.insert(Arrays.asList(vehicle1, vehicle2, vehicle3));

        vehicleDao.findAll().forEach(System.out::println);
    }

}
