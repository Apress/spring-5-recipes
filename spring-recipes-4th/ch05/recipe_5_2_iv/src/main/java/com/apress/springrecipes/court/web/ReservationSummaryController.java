// FINAL
package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.domain.Reservation;
import com.apress.springrecipes.court.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
// Bind controller to URL /reservationSummary
// initial view will be resolved to the name returned in the default GET method
@RequestMapping("/reservationSummary*")
public class ReservationSummaryController {

    private final ReservationService reservationService;

    public ReservationSummaryController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Note date parameter marked a required="true" inside first method
    // If the URL does not match /reservationSummary*?date=* the method will not execute 
    // NOTE: Since this is the default(and only) GET method,
    //       an error will thrown if there is no match(e.g. no date is passed as to URL) 
    @RequestMapping(method = RequestMethod.GET)
    public String generateSummary(
            @RequestParam(required = true, value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate selectedDate, Model model) {
        // Create an empty reservation list
        List<Reservation> reservations = reservationService.findByDate(selectedDate);

        // Now that the date has passed, add reservations (if any)
        // Add reservations object to model so it can be display in a view
        model.addAttribute("reservations", reservations);
        // Check request extension
        /**
         if(request.getServletPath().endsWith(".pdf")) {
         // PDF extension
         // Return view pdfSummary. Via resolver the view
         // is mapped to the class PdfReservationSummary
         // as defined in views.properties
         return "pdfSummary";
         } else if(request.getServletPath().endsWith(".xls")) {
         // Excel extension
         // Return view xlsSummary. Via resolver the view
         // is mapped to the class ExcelReservationSummary
         // as defined in views.properties
         return "excelSummary";
         } else {
         // Any other extension return the file system view reservationSummary
         // Via resolver it will be mapped to /WEB-INF/jsp/reservationSummary.jsp
         return "reservationSummary";
         }*/
        return "reservationSummary";
    }

    @ExceptionHandler
    public void handle(ServletRequestBindingException ex, @RequestParam(required = true, value = "date") String date) {
        if (ex.getRootCause() instanceof ParseException) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.getRootCause().printStackTrace(pw);
            throw new ReservationWebException("Invalid date format for reservation summary", new Date(), sw.toString());
        }
    }
}
