package com.apress.springrecipes.springintegration.myholiday;

import java.util.List;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;


@MessagingGateway
public interface VacationService {

    @Gateway
    List<HotelReservation> findHotels(HotelReservationSearch hotelReservationSearch);
}
