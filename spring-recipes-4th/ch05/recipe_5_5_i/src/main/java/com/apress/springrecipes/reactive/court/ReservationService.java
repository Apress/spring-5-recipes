package com.apress.springrecipes.reactive.court;

import reactor.core.publisher.Flux;

public interface ReservationService {

    Flux<Reservation> query(String query);
    Flux<Reservation> findAll();
}
