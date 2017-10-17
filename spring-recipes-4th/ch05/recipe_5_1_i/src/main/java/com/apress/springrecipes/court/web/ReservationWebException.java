package com.apress.springrecipes.court.web;

import java.util.Date;

public class ReservationWebException extends RuntimeException {
    public static final long serialVersionUID = 1L;
    private String message;
    private String stack;
    private Date date;

    public ReservationWebException(String message, Date date, String stack) {
        this.message = message;
        this.date = date;
        this.stack = stack;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public String getStack() {
        return stack;
    }
}