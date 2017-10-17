package com.apress.springrecipes.springintegration.myholiday;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


public class HotelReservation implements Serializable, Comparable<HotelReservation> {
    private static final long serialVersionUID = 1L;
    private String hotelName;
    private float price;
    private String id;

    public HotelReservation(String hotelName, float price) {
        this.hotelName = hotelName;
        this.price = price;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {

        return String.format("HotelReservation [hotelName=%s, price=%f, id=%s]", this.hotelName, this.price, this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelReservation that = (HotelReservation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int compareTo(HotelReservation o) {
        return this.id.compareTo(o.id);
    }
}
