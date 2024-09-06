package com.antoniomorenoarribas.ecommerce.domain.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import com.antoniomorenoarribas.ecommerce.domain.model.Price;
import com.antoniomorenoarribas.ecommerce.domain.repository.PriceRepository;

public class GetPriceCase {

	private final PriceRepository priceRepository;

    public GetPriceCase(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getFinalPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        List<Price> prices = priceRepository.findApplicablePrices(productId, brandId, applicationDate);
        return prices.stream()
            .max(Comparator.comparing(Price::getPriority))
            .orElseThrow(() -> new RuntimeException("No price available for this product and brand"));
    }
}
