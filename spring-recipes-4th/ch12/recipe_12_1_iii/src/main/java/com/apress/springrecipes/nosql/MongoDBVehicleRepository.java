package com.apress.springrecipes.nosql;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

public class MongoDBVehicleRepository implements VehicleRepository {

    private final MongoTemplate mongo;
    private final String collectionName;

    public MongoDBVehicleRepository(MongoTemplate mongo, String collectionName) {
        this.mongo = mongo;
        this.collectionName = collectionName;
    }

    @Override
    public long count() {
        return mongo.count(null, collectionName);
    }

    @Override
    public void save(Vehicle vehicle) {
        mongo.save(vehicle, collectionName);
    }

    @Override
    public void delete(Vehicle vehicle) {
        mongo.remove(vehicle, collectionName);
    }

    @Override
    public List<Vehicle> findAll() {
        return mongo.findAll(Vehicle.class, collectionName);
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        return mongo.findOne(new Query(where("vehicleNo").is(vehicleNo)), Vehicle.class, collectionName);
    }

    @PreDestroy
    public void cleanUp() {
        mongo.execute(db -> {
            db.drop();
            return null;
        });
    }
}