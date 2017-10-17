package com.apress.springrecipes.reactive.court;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class RandomDataInitializer {

    private static final List<String> NAMES = Arrays.asList("Roger", "James", "Marten", "Josh");
    private final ReservationService reservationService;
    private final Random rnd = new Random();

    public RandomDataInitializer(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostConstruct
    public void init() {



        Flux<Reservation> reservations = Flux.empty();
        for (int i = 0; i < 100 ; i++) {
            int courtNum = rnd.nextInt(3);
            SportType sportType = reservationService.getAllSportTypes().take(1).blockFirst();
            String court = sportType.getName() + " #" + courtNum;

            String name = NAMES.get(rnd.nextInt(NAMES.size()));

            try {
                Reservation res = new Reservation(court, LocalDate.of(2017, rnd.nextInt(12) + 1, rnd.nextInt(28) + 1), rnd.nextInt(24), new Player(name, "N/A"), sportType);
                reservations.concatWith(reservationService.make(res));

            } catch (ReservationNotAvailableException e) {}
        }
        reservations.log().subscribe();
    }


}
