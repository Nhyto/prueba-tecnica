package com.antoniomorenoarribas.ecommerce.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.antoniomorenoarribas.ecommerce.domain.model.Price;
import com.antoniomorenoarribas.ecommerce.infrastructure.database.entities.PriceEntity;

@Repository
public interface PriceRepository {
	
    List<Price> findApplicablePrices(Long productId, Long brandId, LocalDateTime applicationDate);
    
}
