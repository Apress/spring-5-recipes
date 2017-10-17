package com.apress.springrecipes.court;

import com.apress.springrecipes.court.domain.Player;
import com.apress.springrecipes.court.domain.Reservation;
import com.apress.springrecipes.court.domain.SportType;
import com.apress.springrecipes.court.service.ReservationNotAvailableException;
import com.apress.springrecipes.court.service.ReservationService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer {

    private static final List<String> NAMES = Arrays.asList("Roger", "James", "Marten", "Josh");
    private final ReservationService reservationService;
    private final Random rnd = new Random();

    public DataInitializer(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostConstruct
    public void init() {


        List<SportType> sportTypes = reservationService.getAllSportTypes();
        for (int i = 0; i < 100 ; i++) {
            int type = rnd.nextInt(sportTypes.size());
            int courtNum = rnd.nextInt(3);
            SportType sportType = sportTypes.get(type);
            String court = sportType.getName() + " #" + courtNum;

            String name = NAMES.get(rnd.nextInt(NAMES.size()));

            try {
                reservationService.make(new Reservation(court, LocalDate.of(2017, rnd.nextInt(12) + 1, rnd.nextInt(28) + 1), rnd.nextInt(24), new Player(name, "N/A"), sportType));
            } catch (ReservationNotAvailableException e) {}
        }
    }
}
