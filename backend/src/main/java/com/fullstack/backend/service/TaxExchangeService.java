package com.fullstack.backend.service;

import com.fullstack.backend.common.model.ListResponse;
import com.fullstack.backend.model.TaxExchange;

public interface TaxExchangeService {

	TaxExchange createTaxExchange(TaxExchange taxExchange);

    ListResponse listTaxExchanges();
    
    TaxExchange findTaxByDay(Long Diference);

}
