package com.apress.springrecipes.court.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ReservationNotAvailableException extends RuntimeException {

    public static final long serialVersionUID = 1L;
    private final String courtName;
    private final LocalDate date;
    private final int hour;

    public ReservationNotAvailableException(String courtName, LocalDate date, int hour) {
        this.courtName = courtName;
        this.date = date;
        this.hour = hour;
    }

    public String getCourtName() {
        return courtName;
    }

    public Date getDate() {
        return Date.from(this.date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public int getHour() {
        return hour;
    }
}
