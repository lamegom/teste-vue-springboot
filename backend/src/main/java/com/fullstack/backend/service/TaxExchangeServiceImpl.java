package com.fullstack.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.backend.common.model.ListResponse;
import com.fullstack.backend.exception.ExceptionEnum;
import com.fullstack.backend.exception.ExceptionFactory;
import com.fullstack.backend.model.AccountTransfer;
import com.fullstack.backend.model.TaxExchange;
import com.fullstack.backend.repository.TaxExchangeRepository;

@Service
public class TaxExchangeServiceImpl implements TaxExchangeService {

    private TaxExchangeRepository repository;

    @Autowired
    public TaxExchangeServiceImpl(TaxExchangeRepository repository) {
        this.repository = repository;
    }

    @Override
    public TaxExchange createTaxExchange(TaxExchange taxExchange) {
        if(taxExchange == null) {
            throw ExceptionFactory.getApiError(ExceptionEnum.BAD_REQUEST, "All Fields");
        }
        return repository.save(taxExchange);
    }

    @Override
    public ListResponse listTaxExchanges() {
        ListResponse response = new ListResponse();
        List<TaxExchange> taxExchanges = repository.findAll();
        response.setData(taxExchanges);
        response.setTotalCount(taxExchanges.size());
        return response;
    }

	@Override
	public TaxExchange findTaxByDay(Long diference) {

		return repository.findTaxByDiference(diference);
	}
}
