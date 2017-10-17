package com.apress.springrecipes.reactive.court.web;

import com.apress.springrecipes.reactive.court.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/reservationForm")
public class ReservationFormController {

    private final ReservationService reservationService;
    private final ReservationValidator reservationValidator;

    public ReservationFormController(ReservationService reservationService, ReservationValidator reservationValidator) {
        this.reservationService = reservationService;
        this.reservationValidator = reservationValidator;
    }

    @ModelAttribute("sportTypes")
    public Flux<SportType> populateSportTypes() {
        return reservationService.getAllSportTypes();
    }

    @GetMapping
    public String setupForm(@RequestParam(required = false, value = "username") String username, Model model) {
        Player player = new Player(username, null);
        Reservation reservation = new Reservation();
        reservation.setPlayer(player);
        model.addAttribute("reservation", reservation);
        return "reservationForm";
    }

    @PostMapping
    public String submitForm(@Validated @ModelAttribute("reservation") Reservation reservation, BindingResult result) {
        if (result.hasErrors()) {
            return "reservationForm";
        } else {
            reservationService.make(reservation);
            return "redirect:reservationSuccess";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(reservationValidator);
    }
}
