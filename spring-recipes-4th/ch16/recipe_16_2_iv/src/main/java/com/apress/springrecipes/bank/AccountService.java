package com.apress.springrecipes.bank;

public interface AccountService {

    public void createAccount(String accountNo);
    public void removeAccount(String accountNo);
    public void deposit(String accountNo, double amount);
    public void withdraw(String accountNo, double amount);
    public double getBalance(String accountNo);
}
