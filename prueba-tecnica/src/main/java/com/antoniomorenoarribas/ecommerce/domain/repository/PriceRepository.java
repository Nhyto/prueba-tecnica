package com.antoniomorenoarribas.ecommerce.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.antoniomorenoarribas.ecommerce.domain.model.Price;

public interface PriceRepository {
	
    List<Price> findApplicablePrices(Long productId, Long brandId, LocalDateTime applicationDate);
    
}
