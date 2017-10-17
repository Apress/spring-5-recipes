package com.apress.springrecipes.nosql;

import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

public class Main {

    public static void main(String[] args) throws Exception {
        Jedis jedis = new Jedis("localhost");
        ObjectMapper mapper = new ObjectMapper();
        final String vehicleNo = "TEM0001";
        Vehicle vehicle = new Vehicle(vehicleNo, "RED", 4,4);

        jedis.set(vehicleNo, mapper.writeValueAsString(vehicle));

        String vehicleString = jedis.get(vehicleNo);

        System.out.println("Vehicle: " + mapper.readValue(vehicleString, Vehicle.class));
    }
}
