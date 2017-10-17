package com.apress.springrecipes.nosql;

import org.springframework.util.SerializationUtils;

import redis.clients.jedis.Jedis;

public class Main {

    public static void main(String[] args) throws Exception {
        Jedis jedis = new Jedis("localhost");

        final String vehicleNo = "TEM0001";
        Vehicle vehicle = new Vehicle(vehicleNo, "RED", 4,4);

        jedis.set(vehicleNo.getBytes(), SerializationUtils.serialize(vehicle));

        byte[] vehicleArray = jedis.get(vehicleNo.getBytes());

        System.out.println("Vehicle: " + SerializationUtils.deserialize(vehicleArray));
    }
}
