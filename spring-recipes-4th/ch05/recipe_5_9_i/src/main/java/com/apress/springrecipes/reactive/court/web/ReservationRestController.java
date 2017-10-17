package com.apress.springrecipes.reactive.court.web;

import com.apress.springrecipes.reactive.court.Reservation;
import com.apress.springrecipes.reactive.court.ReservationService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ReservationRestController {

    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public Mono<ServerResponse> listAll(ServerRequest request) {
        return ServerResponse.ok().body(reservationService.findAll(), Reservation.class);
    }


    public Mono<ServerResponse> find(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(
                    request.bodyToMono(ReservationQuery.class)
                            .flatMapMany(q -> reservationService.query(q.getCourtName())), Reservation.class);
    }


}
