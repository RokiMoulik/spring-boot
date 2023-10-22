package com.ezybytes.controller;

import com.ezybytes.model.AccountTransaction;
import com.ezybytes.repository.AccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private AccountTransactionsRepository transactionsRepo;

    @GetMapping("/myBalance")
    public List<AccountTransaction> getBalanceDetails(@RequestParam int id){
        System.out.println("/myBalance is called");
        List<AccountTransaction> accountTransactionList = transactionsRepo.findByCustomerIdOrderByTransactionDtDesc(id);
        return (accountTransactionList == null) ? null : accountTransactionList;
    }
}
