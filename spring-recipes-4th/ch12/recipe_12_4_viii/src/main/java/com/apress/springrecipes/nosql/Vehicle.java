package com.apress.springrecipes.nosql;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

public class Vehicle {

    @Id
    private String vehicleNo;
    @Field
    private String color;
    @Field
    private int wheel;
    @Field
    private int seat;

    public Vehicle() {
    }

    public Vehicle(String vehicleNo, String color, int wheel, int seat) {
        this.vehicleNo = vehicleNo;
        this.color = color;
        this.wheel = wheel;
        this.seat = seat;
    }

    public String getColor() {
        return color;
    }

    public int getSeat() {
        return seat;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public int getWheel() {
        return wheel;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setWheel(int wheel) {
        this.wheel = wheel;
    }

    @Override
    public String toString() {
        return "Vehicle [" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", color='" + color + '\'' +
                ", wheel=" + wheel +
                ", seat=" + seat +
                ']';
    }
}
