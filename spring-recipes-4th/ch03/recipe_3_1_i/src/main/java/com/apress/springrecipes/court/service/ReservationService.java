package com.apress.springrecipes.court.service;

import com.apress.springrecipes.court.domain.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> query(String courtName);


}
