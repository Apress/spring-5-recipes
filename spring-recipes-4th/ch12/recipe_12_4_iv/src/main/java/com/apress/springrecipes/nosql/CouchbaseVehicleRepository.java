package com.apress.springrecipes.nosql;

import java.io.IOException;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class CouchbaseVehicleRepository implements VehicleRepository {

    private final Bucket bucket;
    private final ObjectMapper mapper;

    public CouchbaseVehicleRepository(Bucket bucket, ObjectMapper mapper) {
        this.bucket=bucket;
        this.mapper=mapper;
    }

    @Override
    public void save(Vehicle vehicle) {

        String json = null;
        try {
            json = mapper.writeValueAsString(vehicle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error encoding JSON.", e);
        }
        JsonObject vehicleJson = JsonObject.fromJson(json);
        JsonDocument vehicleDoc = JsonDocument.create(vehicle.getVehicleNo(), vehicleJson);
        bucket.upsert(vehicleDoc);
    }

    @Override
    public void delete(Vehicle vehicle) {
        bucket.remove(vehicle.getVehicleNo());
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        JsonDocument doc = bucket.get(vehicleNo, JsonDocument.class);
        if (doc != null) {
            JsonObject result = doc.content();
            try {
                return mapper.readValue(result.toString(), Vehicle.class);
            } catch (IOException e) {
                throw new RuntimeException("Error decoding JSON.", e);
            }
        }
        return null;
    }
}
