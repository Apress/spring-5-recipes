package com.apress.springrecipes.reactive.court;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class ReservationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
//        return Reservation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@Nullable Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courtName",
                "required.courtName", "Court name is required.");
        ValidationUtils.rejectIfEmpty(errors, "date",
                "required.date", "Date is required.");
        ValidationUtils.rejectIfEmpty(errors, "hour",
                "required.hour", "Hour is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "player.name",
                "required.playerName", "Player name is required.");
        ValidationUtils.rejectIfEmpty(errors, "sportType",
                "required.sportType", "Sport type is required.");

        Reservation reservation = (Reservation) target;
        LocalDate date = reservation.getDate();
        int hour = reservation.getHour();
        if (date != null) {
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                if (hour < 8 || hour > 22) {
                    errors.reject("invalid.holidayHour", "Invalid holiday hour.");
                }
            } else {
                if (hour < 9 || hour > 21) {
                    errors.reject("invalid.weekdayHour", "Invalid weekday hour.");
                }
            }
        }

    }
}
