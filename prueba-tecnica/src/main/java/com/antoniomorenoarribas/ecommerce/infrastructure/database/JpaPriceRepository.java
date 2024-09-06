package com.antoniomorenoarribas.ecommerce.infrastructure.database;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.antoniomorenoarribas.ecommerce.application.mappers.PriceMapper;
import com.antoniomorenoarribas.ecommerce.domain.model.Price;
import com.antoniomorenoarribas.ecommerce.infrastructure.database.entities.PriceEntity;

public class JpaPriceRepository {

    private SpringDataPriceRepository springDataPriceRepository;
	
	private PriceMapper priceMapper;
	
	
	@Autowired
	public JpaPriceRepository(SpringDataPriceRepository springDataPriceRepository, PriceMapper priceMapper) {
		super();
		this.springDataPriceRepository = springDataPriceRepository;
		this.priceMapper = priceMapper;
	}



	public List<Price> findApplicablePrices(Long productId, Long brandId, LocalDateTime applicationDate) {
	 
	        List<PriceEntity> priceEntities = springDataPriceRepository.findByProductIdAndBrandIdAndStartDateBetween(
	            productId, brandId, applicationDate, applicationDate);

	        return priceEntities.stream()
	            .map(priceMapper::toDomain) 
	            .collect(Collectors.toList());
	}
	
}
