package com.apress.springrecipes.court.domain;

import javax.validation.constraints.NotNull;

public class Player {

    @NotNull
    private String name;

    @NotNull
    private String phone;

    public Player() {
    }

    public Player(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
