package com.apress.springrecipes.bank.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IBANValidationResult {

    private boolean valid;
    private List<String> messages = new ArrayList<>();
    private String iban;

    private Map<String, String> bankData = new HashMap<>();

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Map<String, String> getBankData() {
        return bankData;
    }

    public void setBankData(Map<String, String> bankData) {
        this.bankData = bankData;
    }

    @Override
    public String toString() {
        return "IBANValidationResult [" +
                "valid=" + valid +
                ", messages=" + messages +
                ", iban='" + iban + '\'' +
                ", bankData=" + bankData +
                ']';
    }
}
