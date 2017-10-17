package com.apress.springrecipes.springintegration;

public class Customer {
    private final long id;
    private String firstName;
    private String lastName;
    private long creditScore;

    public Customer(long id, String firstName, String lastname, long creditScore) {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastname;
        this.creditScore=creditScore;
    }

    public long getId() {
        return id;
    }

    public long getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(final long creditScore) {
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

     @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", creditScore=" + creditScore +
                '}';
    }
}
