package com.apress.springrecipes.nosql;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    public static void main(String[] args) {

        Cluster cluster = CouchbaseCluster.create();
        Bucket bucket = cluster.openBucket();

        CouchbaseVehicleRepository vehicleRepository = new CouchbaseVehicleRepository(bucket, new ObjectMapper());
        vehicleRepository.save(new Vehicle("TEM0001", "GREEN", 3, 1));
        vehicleRepository.save(new Vehicle("TEM0004", "RED", 4, 2));

        System.out.println("Vehicle: " + vehicleRepository.findByVehicleNo("TEM0001"));
        System.out.println("Vehicle: " + vehicleRepository.findByVehicleNo("TEM0004"));

        bucket.remove("TEM0001");
        bucket.remove("TEM0004");

        bucket.close();
        cluster.disconnect();
    }
}
