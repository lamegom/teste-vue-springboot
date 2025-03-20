package com.fullstack.backend.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.backend.common.model.ListResponse;
import com.fullstack.backend.exception.ApiError;
import com.fullstack.backend.exception.ExceptionEnum;
import com.fullstack.backend.exception.ExceptionFactory;
import com.fullstack.backend.model.AccountTransfer;
import com.fullstack.backend.model.TaxExchange;
import com.fullstack.backend.repository.AccountTransferRepository;

@Service
public class AccountTransferServiceImpl implements AccountTransferService {
	@Autowired
    private AccountTransferRepository repository;
	@Autowired
	private TaxExchangeService taxExchangeService;

    @Override
    public AccountTransfer createAccountTransfer(AccountTransfer accountTransfer) {
        if(accountTransfer == null) {
            throw ExceptionFactory.getApiError(ExceptionEnum.BAD_REQUEST, "All Fields");
        }
        
        accountTransfer.setTransferDate(new Date());
        long dif = getDifferenceDays(accountTransfer.getTransferDate(), accountTransfer.getScheduleDate());
        TaxExchange tax = taxExchangeService.findTaxByDay(dif);
        Double t = 0.0;
        if(Objects.nonNull(tax)) {
        	if(accountTransfer.getAmount() >= tax.getAmmount() ) {
        		t =tax.getTax();
        	}
        }
        accountTransfer.setTax(t);
        
        
        return repository.save(accountTransfer);
    }
    
    public long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public AccountTransfer updateAccountTransfer(AccountTransfer updatedAccountTransfer) {
        if(updatedAccountTransfer == null) {
            throw ExceptionFactory.getApiError(ExceptionEnum.BAD_REQUEST, "All Fields");
        }


        return repository.save(updatedAccountTransfer);
    }

    @Override
    public boolean deleteAccountTransferById(Long id) {
        if(id == null) {
            throw ExceptionFactory.getApiError(ExceptionEnum.BAD_REQUEST, "accountTransferId");
        }
        Optional<AccountTransfer> accountTransfer = repository.findById(id);
        if(!accountTransfer.isPresent()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public AccountTransfer getAccountTransferById(Long id) throws ApiError {
        if(id == null) {
            throw ExceptionFactory.getApiError(ExceptionEnum.BAD_REQUEST, "accountTransferId");
        }
        try {
            Optional<AccountTransfer> accountTransfer = repository.findById(id);
            if(!accountTransfer.isPresent()) {
                throw ExceptionFactory.getApiError(ExceptionEnum.NOT_FOUND, "accountTransfer");
            }
            return accountTransfer.get();
        } catch (ApiError error) {
            throw error;
        }
    }

    @Override
    public ListResponse listAccountTransfers() {
        ListResponse response = new ListResponse();
        List<AccountTransfer> accountTransfers = repository.findAll();
        response.setData(accountTransfers);
        response.setTotalCount(accountTransfers.size());
        return response;
    }
}
