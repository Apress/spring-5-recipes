package com.apress.springrecipes.springintegration.myholiday;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class HotelReservationSearch implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int roomsDesired;
    private final Date start;
    private final Date stop;
    private final float maximumPrice;

    public HotelReservationSearch() {
        this(0,0, null,null);
    }

    public HotelReservationSearch(float maximumPrice, int roomsDesired, Date start, Date stop) {
        super();
        this.maximumPrice = maximumPrice;
        this.roomsDesired = roomsDesired;
        this.start = start;
        this.stop = stop;
    }

    public int getRoomsDesired() {
        return roomsDesired;
    }

    public Date getStart() {
        return start;
    }

    public Date getStop() {
        return stop;
    }

    public float getMaximumPrice() {
        return maximumPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelReservationSearch that = (HotelReservationSearch) o;
        return roomsDesired == that.roomsDesired &&
                Float.compare(that.maximumPrice, maximumPrice) == 0 &&
                Objects.equals(start, that.start) &&
                Objects.equals(stop, that.stop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomsDesired, start, stop, maximumPrice);
    }

    @Override
    public String toString() {
        return String.format("HotelReservationSearch [roomsDesired=%d, start=%t, stop=%t, maximumPrice=%f]", roomsDesired, start, stop, maximumPrice);
    }
}
