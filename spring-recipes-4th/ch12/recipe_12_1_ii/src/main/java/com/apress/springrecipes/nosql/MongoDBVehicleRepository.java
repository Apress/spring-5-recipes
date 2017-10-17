package com.apress.springrecipes.nosql;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class MongoDBVehicleRepository implements VehicleRepository {

    private final MongoClient mongo;
    private final String collectionName;
    private final String databaseName;

    public MongoDBVehicleRepository(MongoClient mongo, String databaseName, String collectionName) {
        this.mongo = mongo;
        this.databaseName = databaseName;
        this.collectionName = collectionName;
    }

    @Override
    public long count() {
        return getCollection().count();
    }

    @Override
    public void save(Vehicle vehicle) {
        Document dbVehicle = transform(vehicle);
        getCollection().insertOne(dbVehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        getCollection().deleteOne(eq("vehicleNo", vehicle.getVehicleNo()));
    }

    @Override
    public List<Vehicle> findAll() {
        return StreamSupport.stream(getCollection().find().spliterator(), false)
                .map(this::transform)
                .collect(Collectors.toList());
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        return transform(getCollection().find(eq("vehicleNo", vehicleNo)).first());
    }

    private MongoCollection<Document> getCollection() {
        return mongo.getDatabase(databaseName).getCollection(collectionName);
    }

    /**
     * Transform a {@code DBObject} into a {@code Vehicle}
     *
     * @param dbVehicle the dbobject
     * @return newly constructed Vehicle instance
     */

    private Vehicle transform(Document dbVehicle) {
        if (dbVehicle == null) {
            return null;
        }
        return new Vehicle(
                dbVehicle.getString("vehicleNo"),
                dbVehicle.getString("color"),
                dbVehicle.getInteger("wheel"),
                dbVehicle.getInteger("seat"));
    }

    /**
     * Transform a {@code Vehicle} into a {@DBObject} for storage or querying.
     *
     * @param vehicle the vehicle
     * @return DBObject based on the Vehicle properties
     */
    private Document transform(Vehicle vehicle) {
        return new Document("vehicleNo", vehicle.getVehicleNo())
                .append("color", vehicle.getColor())
                .append("wheel", vehicle.getWheel())
                .append("seat", vehicle.getSeat());
    }

    @PreDestroy
    public void cleanUp() {
        mongo.dropDatabase(databaseName);
    }
}
