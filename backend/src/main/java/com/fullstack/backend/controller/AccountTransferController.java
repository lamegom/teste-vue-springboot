package com.fullstack.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.backend.common.model.ListResponse;
import com.fullstack.backend.model.AccountTransfer;
import com.fullstack.backend.service.AccountTransferService;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api/accountTransfer")
public class AccountTransferController {

    private AccountTransferService accountTransferService;

    @Autowired
    public AccountTransferController(AccountTransferService accountTransferService) {
        this.accountTransferService = accountTransferService;
    }

    @PostMapping("/save")
    public AccountTransfer saveAccountTransfer(@RequestBody AccountTransfer accountTransfer) {
        return accountTransferService.createAccountTransfer(accountTransfer);
    }

    @PutMapping("/update")
    public AccountTransfer updateAccountTransfer(@RequestBody AccountTransfer accountTransfer) {
        return accountTransferService.updateAccountTransfer(accountTransfer);
    }

    @DeleteMapping("/delete")
    public Boolean deleteAccountTransfer(@RequestParam Long id) {
        return accountTransferService.deleteAccountTransferById(id);
    }

    @GetMapping("/get")
    public AccountTransfer getAccountTransferById(@RequestParam Long id) {
        return accountTransferService.getAccountTransferById(id);
    }

    @GetMapping("/list")
    public ListResponse listAccountTransfers() {
        return accountTransferService.listAccountTransfers();
    }

}
