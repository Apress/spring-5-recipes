package com.apress.springrecipes.nosql;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

public class MongoDBVehicleRepository implements VehicleRepository {

    private final MongoTemplate mongo;

    public MongoDBVehicleRepository(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    @Override
    public long count() {
        return mongo.count(null, Vehicle.class);
    }

    @Override
    public void save(Vehicle vehicle) {
        mongo.save(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        mongo.remove(vehicle);
    }

    @Override
    public List<Vehicle> findAll() {
        return mongo.findAll(Vehicle.class);
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        return mongo.findOne(new Query(where("vehicleNo").is(vehicleNo)), Vehicle.class);
    }


    @PreDestroy
    public void cleanUp() {
        mongo.execute(db -> {
            db.drop();
            return null;
        });
    }
}