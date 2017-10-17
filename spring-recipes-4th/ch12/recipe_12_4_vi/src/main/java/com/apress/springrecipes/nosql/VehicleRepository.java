package com.apress.springrecipes.nosql;


public interface VehicleRepository {

    void save(Vehicle vehicle);

    void delete(Vehicle vehicle);

    Vehicle findByVehicleNo(String vehicleNo);

}
