package com.apress.springrecipes.nosql;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.SerializableDocument;

class CouchbaseVehicleRepository implements VehicleRepository {

    private final Bucket bucket;

    public CouchbaseVehicleRepository(Bucket bucket) {
        this.bucket=bucket;
    }

    @Override
    public void save(Vehicle vehicle) {
        SerializableDocument vehicleDoc = SerializableDocument.create(vehicle.getVehicleNo(), vehicle);
        bucket.upsert(vehicleDoc);
    }

    @Override
    public void delete(Vehicle vehicle) {
        bucket.remove(vehicle.getVehicleNo());
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        SerializableDocument doc = bucket.get(vehicleNo, SerializableDocument.class);
        if (doc != null) {
            return (Vehicle) doc.content();
        }
        return null;
    }
}
