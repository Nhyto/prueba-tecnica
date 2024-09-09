package com.antoniomorenoarribas.ecommerce.infrastructure.database;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.antoniomorenoarribas.ecommerce.infrastructure.database.entities.PriceEntity;

public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {
	
	List<PriceEntity> findByProductIdAndBrandIdAndStartDateBetween(
		    Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate);

}
