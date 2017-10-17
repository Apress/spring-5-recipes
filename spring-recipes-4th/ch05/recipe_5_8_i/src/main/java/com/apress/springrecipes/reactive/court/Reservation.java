package com.apress.springrecipes.reactive.court;

import java.time.LocalDate;

public class Reservation {

    private String courtName;
    private LocalDate date;
    private int hour;
    private Player player = new Player();
    private SportType sportType = new SportType();

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

    @Override
    public String toString() {
        final String format = "Reservation[courtName=%s, player=%s, sportType=%s, date=%s, hour=%s]";
        return String.format(format, this.courtName, this.player.getName(), this.sportType.getName(), date.toString(), String.valueOf(this.hour));
    }
}
