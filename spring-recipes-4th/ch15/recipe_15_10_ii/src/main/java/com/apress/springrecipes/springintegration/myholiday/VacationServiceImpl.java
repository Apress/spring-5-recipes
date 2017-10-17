package com.apress.springrecipes.springintegration.myholiday;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.integration.annotation.ServiceActivator;


public class VacationServiceImpl implements VacationService {
    private List<HotelReservation> hotelReservations;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        hotelReservations = Arrays.asList(
                new HotelReservation("Bilton", 243.200F),
                new HotelReservation("East Western", 75.0F),
                new HotelReservation("Thairfield Inn", 70F),
                new HotelReservation("Park In The Inn", 200.00F));
    }

    @ServiceActivator
    public List<HotelReservation> findHotels(HotelReservationSearch searchMsg) {
        try {
            Thread.sleep(1000);
        } catch (Throwable th) {
        }

        return this.hotelReservations;
    }
}
