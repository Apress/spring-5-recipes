package com.apress.springrecipes.bank.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apress.springrecipes.bank.AccountService;

@Controller
public class DepositController {

    private AccountService accountService;

    public DepositController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/deposit")
    protected String deposit(
            @RequestParam("accountNo") String accountNo,
            @RequestParam("amount") double amount,
            ModelMap model) {
        accountService.deposit(accountNo, amount);
        model.addAttribute("accountNo", accountNo);
        model.addAttribute("balance", accountService.getBalance(accountNo));
        return "success";
    }
}
