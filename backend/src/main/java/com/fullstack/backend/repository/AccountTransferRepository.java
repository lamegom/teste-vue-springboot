package com.fullstack.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.backend.model.AccountTransfer;

public interface AccountTransferRepository extends JpaRepository<AccountTransfer, Long> {
}
