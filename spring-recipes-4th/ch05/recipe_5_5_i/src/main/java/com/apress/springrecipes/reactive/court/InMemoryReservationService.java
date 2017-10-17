package com.apress.springrecipes.reactive.court;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryReservationService implements ReservationService {
    public static final SportType TENNIS = new SportType(1, "Tennis");
    public static final SportType SOCCER = new SportType(2, "Soccer");

    private final List<Reservation> reservations = new ArrayList<>();

    public InMemoryReservationService() {

        reservations.add(new Reservation("Tennis #1", LocalDate.of(2008, 1, 14), 16,
                new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #2", LocalDate.of(2008, 1, 14), 20,
                new Player("James", "N/A"), TENNIS));
    }

    @Override
    public Flux<Reservation> query(String courtName) {
        if (courtName != null) {
            return findAll()
                    .filter(r -> r.getCourtName().startsWith(courtName));
        }
        return Flux.empty();
    }

    @Override
    public Flux<Reservation> findAll() {
        return Flux.fromIterable(reservations);
    }
}
