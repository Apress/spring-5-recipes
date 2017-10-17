package com.apress.springrecipes.court.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import com.apress.springrecipes.court.domain.PeriodicReservation;
import com.apress.springrecipes.court.domain.PeriodicReservationValidator;
import com.apress.springrecipes.court.domain.Player;
import com.apress.springrecipes.court.service.ReservationService;


@Controller
// Bind controller to URL /periodicReservationForm
// initial view will be resolved to the name returned in the default GET method
@RequestMapping("/periodicReservationForm")
// Add Reservation object to session, since its used in various pages/forms
@SessionAttributes("reservation")
public class PeriodicReservationController {

    private final Map<Integer, String> pageForms = new HashMap<>(3);
    private final ReservationService reservationService;
    private final PeriodicReservationValidator validator;

    // Wire service and validator in constructor, available in application context 
    public PeriodicReservationController(ReservationService reservationService,
                                         PeriodicReservationValidator periodicReservationValidator) {
        this.reservationService = reservationService;
        this.validator = periodicReservationValidator;
    }

    @PostConstruct
    public void initialize() {
        pageForms.put(0, "reservationCourtForm");
        pageForms.put(1, "reservationTimeForm");
        pageForms.put(2, "reservationPlayerForm");
    }

    // Controller will always look for a default GET method to call first, irrespective of name
    // In this case, named setupForm to ease identification
    @GetMapping
    // Method contain a Model input to setup reservation object
    public String setupForm(Model model) {
        // Create inital reservation object
        PeriodicReservation reservation = new PeriodicReservation();
        // Set player for reservation object
        reservation.setPlayer(new Player());
        // Add reservation to model so it can be display in views
        model.addAttribute("reservation", reservation);
        // Return view as a string
        // Based on resolver configuration the reservationCourtForm view
        // will be mapped to a JSP in /WEB-INF/jsp/reservationCourtForm.jsp
        return "reservationCourtForm";
    }

    // Post Mapping which will be called when the _cancel property is present in the URL
    @PostMapping(params = {"_cancel"})
    public String cancelForm(
            @ModelAttribute("reservation") PeriodicReservation reservation, BindingResult result,
            @RequestParam("_page") int currentPage) {
        return pageForms.get(currentPage);

    }

    // Register the validator so that we can use @Validated to validate the entire object.
    // Instead we could also have called validator.validate in the completeForm method
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(this.validator);
    }

    // Post Mapping which will be called when the _finish property is present in the URL
    @PostMapping(params = {"_finish"})
    public String completeForm(
            @Validated @ModelAttribute("reservation") PeriodicReservation reservation,
            BindingResult result, SessionStatus status,
            @RequestParam("_page") int currentPage) {
        if (!result.hasErrors()) {
            // No errors make reservation
            reservationService.makePeriodic(reservation);
            // Set complete, mark the handler's session processing as complete
            // Allowing for cleanup of session attributes.
            status.setComplete();
            // Redirect to reservationSuccess URL, defined in ReservationSuccessController
            return "redirect:reservationSuccess";
        } else {
            // Errors, this should always be "reservationPlayerForm" since its the last one, use HashMap anyways
            // return user to current view to correct
            return pageForms.get(currentPage);
        }
    }

    // Controller will always look for a default POST method irrespective of name
    // when a submission ocurrs on the URL (i.e.@RequestMapping(/periodicReservationForm)) 
    // In this case, named submitForm to ease identification
    @PostMapping
    public String submitForm(
            HttpServletRequest request,
            @ModelAttribute("reservation") PeriodicReservation reservation,
            BindingResult result, @RequestParam("_page") int currentPage) {
        // Extract target page
        int targetPage = getTargetPage(request, "_target", currentPage);
        // If targetPage is lesser than current page, user clicked 'Previous'
        if (targetPage < currentPage) {
            // Return to target page view
            return pageForms.get(targetPage);
        }
        // User clicked 'Next'
        // Validate data based on page
        validateCurrentPage(reservation, result, currentPage);
        if (!result.hasErrors()) {
            // No errors, return target page
            return pageForms.get(targetPage);
        } else {
            // Errors, return current page
            return pageForms.get(currentPage);
        }
    }

    private void validateCurrentPage(PeriodicReservation reservation, BindingResult result, int currentPage) {
        switch (currentPage) {
            case 0:
                validator.validateCourt(reservation, result);
                break;
            case 1:
                validator.validateTime(reservation, result);
                break;
            case 2:
                validator.validatePlayer(reservation, result);
                break;
        }
    }

    // Create attribute for model 
    // Will be represented as drop box containing "Daily, Weekly" values in reservationTimeForm
    @ModelAttribute("periods")
    public Map<Integer, String> periods() {
        Map<Integer, String> periods = new HashMap<>();
        periods.put(1, "Daily");
        periods.put(7, "Weekly");
        return periods;
    }

    /**
     * Prior to Spring 5 this code was part of the {@code WebUtils} utility class (with a deprecation notice). For use
     * with the newer version of Spring the code has been moved here.
     *
     * @param request     the request
     * @param paramPrefix the prefix to look for
     * @param currentPage the current page.
     * @return the tagetPage number when found otherwise currentPage
     */
    private int getTargetPage(HttpServletRequest request, String paramPrefix, int currentPage) {
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            if (paramName.startsWith(paramPrefix)) {
                for (int i = 0; i < WebUtils.SUBMIT_IMAGE_SUFFIXES.length; i++) {
                    String suffix = WebUtils.SUBMIT_IMAGE_SUFFIXES[i];
                    if (paramName.endsWith(suffix)) {
                        paramName = paramName.substring(0, paramName.length() - suffix.length());
                    }
                }
                return Integer.parseInt(paramName.substring(paramPrefix.length()));
            }
        }
        return currentPage;


    }
}
