package com.fullstack.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.backend.common.model.ListResponse;
import com.fullstack.backend.model.TaxExchange;
import com.fullstack.backend.service.TaxExchangeService;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api/taxExchange")
public class TaxExchangeController {

    private TaxExchangeService taxExchangeService;

    @Autowired
    public TaxExchangeController(TaxExchangeService taxExchangeService) {
        this.taxExchangeService = taxExchangeService;
    }

    @PostMapping("/save")
    public TaxExchange saveTaxExchange(@RequestBody TaxExchange taxExchange) {
        return taxExchangeService.createTaxExchange(taxExchange);
    }


    @GetMapping("/list")
    public ListResponse listTaxExchanges() {
        return taxExchangeService.listTaxExchanges();
    }

}
