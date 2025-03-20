package com.fullstack.backend.service;

import com.fullstack.backend.common.model.ListResponse;
import com.fullstack.backend.model.AccountTransfer;

public interface AccountTransferService {

	AccountTransfer createAccountTransfer(AccountTransfer accountTransfer);

	AccountTransfer updateAccountTransfer(AccountTransfer accountTransfer);

    boolean deleteAccountTransferById(Long id);

    AccountTransfer getAccountTransferById(Long id);

    ListResponse listAccountTransfers();

}
