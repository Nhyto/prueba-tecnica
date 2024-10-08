package com.antoniomorenoarribas.ecommerce.infrastructure.database;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.antoniomorenoarribas.ecommerce.infrastructure.database.entities.PriceEntity;

public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {
	
	@Query(value = "SELECT * FROM PRICES p " +
            "WHERE p.PRODUCT_ID = :productId " +
            "AND p.BRAND_ID = :brandId " +
            "AND :applicationDate BETWEEN p.START_DATE AND p.END_DATE " +
            "ORDER BY p.PRIORITY DESC " +
            "LIMIT 1", 
    nativeQuery = true)
	Optional<PriceEntity> findTopByProductIdAndBrandIdAndApplicationDateOrderByPriorityDesc(
			 @Param("productId") Long productId, 
	         @Param("brandId") Long brandId, 
	         @Param("applicationDate") LocalDateTime applicationDate);
	}
