package com.antoniomorenoarribas.ecommerce.domain.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.antoniomorenoarribas.ecommerce.domain.model.Price;

@Service
public class GetPriceCase {

    public Price getFinalPrice(List<Price> prices) {
    	
    	if (Objects.isNull(prices)) {
            throw new IllegalArgumentException("The price list cannot be null");
        }
    	
        return prices.stream()
            .max(Comparator.comparing(Price::getPriority))
            .orElseThrow(() -> new RuntimeException("No price available for this product and brand"));
    }
}
