package com.apress.springrecipes.springintegration;

public class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private String telephone;
    private float creditScore;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public float getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(final float creditScore) {
        this.creditScore = creditScore;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }
}
