package com.apress.springrecipes.court.domain;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Member {

    private String name;
    private String phone;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
