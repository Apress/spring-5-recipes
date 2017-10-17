package com.apress.springrecipes.nosql;


import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {

}
