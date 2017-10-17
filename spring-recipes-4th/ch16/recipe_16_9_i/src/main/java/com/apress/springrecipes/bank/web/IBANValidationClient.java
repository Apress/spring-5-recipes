package com.apress.springrecipes.bank.web;

public interface IBANValidationClient {

    IBANValidationResult validate(String iban);
}
