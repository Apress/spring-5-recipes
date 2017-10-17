package com.apress.springrecipes.court.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Reservation {

    @NotNull
    @Size(min=4)
    private String courtName;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Min(8)
    @Max(22)
    private int hour;

    @Valid
    private Player player;

    @NotNull
    private SportType sportType;

    public Reservation() {
    }

    public Reservation(String courtName, LocalDate date, int hour, Player player, SportType sportType) {
        this.courtName = courtName;
        this.date = date;
        this.hour = hour;
        this.player = player;
        this.sportType = sportType;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public Date getDateAsUtilDate() {
        return Date.from(this.date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
