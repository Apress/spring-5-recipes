package com.apress.springrecipes.reactive.court.web;

import com.apress.springrecipes.reactive.court.Reservation;
import com.apress.springrecipes.reactive.court.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {

    private final ReservationService reservationService;

    public ReservationQueryController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public void setupForm() {
    }

    @PostMapping
    public String sumbitForm(ServerWebExchange serverWebExchange, Model model) {

        Flux<Reservation> reservations = serverWebExchange.getFormData()
                                            .map(form -> form.get("courtName"))
                                            .flatMapMany(Flux::fromIterable)
                                            .concatMap(courtName -> reservationService.query(courtName));

        model.addAttribute("reservations", reservations);
        return "reservationQuery";
    }

}
