package com.apress.springrecipes.nosql;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

class CouchbaseVehicleRepository implements VehicleRepository {

    private final Bucket bucket;

    public CouchbaseVehicleRepository(Bucket bucket) {
        this.bucket=bucket;
    }

    @Override
    public void save(Vehicle vehicle) {

        JsonObject vehicleJson = JsonObject.empty()
                .put("vehicleNo", vehicle.getVehicleNo())
                .put("color", vehicle.getColor())
                .put("wheels", vehicle.getWheel())
                .put("seat", vehicle.getSeat());

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
            return new Vehicle(result.getString("vehicleNo"), result.getString("color"), result.getInt("wheels"), result.getInt("seat"));
        }
        return null;
    }
}
