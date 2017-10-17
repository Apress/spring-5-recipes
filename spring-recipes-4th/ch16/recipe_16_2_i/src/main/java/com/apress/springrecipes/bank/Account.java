package com.apress.springrecipes.bank;

import java.util.Objects;

public class Account {

    private final String accountNo;
    private double balance;

    /**
     * Default constructor because JPA demands it.
     */
    private Account() {
        this(null, 0.0d);
    }

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNo, account.accountNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo);
    }

    @Override
    public String toString() {
        return "Account [" +
                "accountNo='" + accountNo + '\'' +
                ", balance=" + balance +
                ']';
    }
}
