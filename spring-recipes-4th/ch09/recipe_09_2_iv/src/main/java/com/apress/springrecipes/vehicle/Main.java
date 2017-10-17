package com.apress.springrecipes.vehicle;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.vehicle.config.VehicleConfiguration;

/**
 * Created by marten on 24-03-14.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(VehicleConfiguration.class);

        VehicleDao vehicleDao = context.getBean(VehicleDao.class);
        List<Vehicle> vehicles = vehicleDao.findAll();
        for (Vehicle vehicle : vehicles) {
            System.out.println("Vehicle No: " + vehicle.getVehicleNo());
            System.out.println("Color: " + vehicle.getColor());
            System.out.println("Wheel: " + vehicle.getWheel());
            System.out.println("Seat: " + vehicle.getSeat());
        }

    }

}
