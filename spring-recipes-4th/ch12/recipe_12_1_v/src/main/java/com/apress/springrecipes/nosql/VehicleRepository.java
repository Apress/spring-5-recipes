package com.apress.springrecipes.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    Vehicle findByVehicleNo(String vehicleNo);

}
