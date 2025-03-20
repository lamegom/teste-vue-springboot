package com.fullstack.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.backend.model.TaxExchange;

public interface TaxExchangeRepository extends JpaRepository<TaxExchange, Long> {
	@Query("from TaxExchange where :dif between fromTransferDay and untilTransferDay")    
	public TaxExchange findTaxByDiference(@Param("dif") Long difference);
}
