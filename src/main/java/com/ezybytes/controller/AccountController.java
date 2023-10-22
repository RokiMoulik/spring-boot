package com.ezybytes.controller;

import com.ezybytes.model.Accounts;
import com.ezybytes.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountsRepository accountsRepo;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam int id){
        System.out.println("/myAccount is called");
        Accounts accounts = accountsRepo.findByCustomerId(id);

        if(accounts != null) return accounts;
        return null;
    }
}
