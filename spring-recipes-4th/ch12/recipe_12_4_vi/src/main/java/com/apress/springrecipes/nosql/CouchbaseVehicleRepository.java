package com.apress.springrecipes.nosql;

import org.springframework.data.couchbase.core.CouchbaseTemplate;

public class CouchbaseVehicleRepository implements VehicleRepository {

    private final CouchbaseTemplate couchbase;

    public CouchbaseVehicleRepository(CouchbaseTemplate couchbase) {
        this.couchbase = couchbase;
    }

    @Override
    public void save(Vehicle vehicle) {
        couchbase.save(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        couchbase.remove(vehicle);
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        return couchbase.findById(vehicleNo, Vehicle.class);
    }
}
