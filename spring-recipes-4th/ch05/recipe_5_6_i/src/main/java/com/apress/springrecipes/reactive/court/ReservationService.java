package com.apress.springrecipes.reactive.court;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationService {

    Flux<Reservation> query(String query);
    Flux<Reservation> findAll();
    Flux<SportType> getAllSportTypes();
    Mono<Reservation> make(Reservation reservation);

    SportType getSportType(int sportTypeId);
}
