package com.antoniomorenoarribas.ecommerce.infrastructure.database;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.antoniomorenoarribas.ecommerce.application.mappers.PriceMapper;
import com.antoniomorenoarribas.ecommerce.domain.model.Price;
import com.antoniomorenoarribas.ecommerce.domain.repository.PriceRepository;
import com.antoniomorenoarribas.ecommerce.infrastructure.database.entities.PriceEntity;

@Repository
public class JpaPriceRepository implements PriceRepository {

    private SpringDataPriceRepository springDataPriceRepository;
	private PriceMapper priceMapper;
	
	@Autowired
	public JpaPriceRepository(SpringDataPriceRepository springDataPriceRepository, PriceMapper priceMapper) {
		super();
		this.springDataPriceRepository = springDataPriceRepository;
		this.priceMapper = priceMapper;
	}

	@Override
	public List<Price> findApplicablePrices(Long productId, Long brandId, LocalDateTime applicationDate) {
	    // Ajustamos la búsqueda para verificar que la applicationDate esté entre startDate y endDate
	    List<PriceEntity> priceEntities = springDataPriceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
	        productId, brandId, applicationDate, applicationDate);

	    return priceEntities.stream()
	        .map(priceMapper::toDomain) 
	        .collect(Collectors.toList());
	}
}
