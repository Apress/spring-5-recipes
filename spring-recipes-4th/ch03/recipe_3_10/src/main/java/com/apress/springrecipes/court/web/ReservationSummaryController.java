package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.domain.Reservation;
import com.apress.springrecipes.court.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reservationSummary*")
public class ReservationSummaryController {

    private final ReservationService reservationService;

    // Wire service available in application context 
    @Autowired
    public ReservationSummaryController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String generateSummary(
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate selectedDate, Model model) {
        List<Reservation> reservations = reservationService.findByDate(selectedDate);

        model.addAttribute("reservations", reservations);
        return "reservationSummary";
    }
}
