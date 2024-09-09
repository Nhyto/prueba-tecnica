package com.antoniomorenoarribas.ecommerce.domain.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.antoniomorenoarribas.ecommerce.domain.exceptions.InvalidPriceListException;
import com.antoniomorenoarribas.ecommerce.domain.exceptions.PriceNotAvailableException;
import com.antoniomorenoarribas.ecommerce.domain.model.Price;

@Service
public class GetPriceCase {

    public Price getFinalPrice(List<Price> prices) {
    	
    	if (Objects.isNull(prices)) {
            throw new InvalidPriceListException("La lista de precios no puede ser nula");
        }
    	
        return prices.stream()
            .max(Comparator.comparing(Price::getPriority))
            .orElseThrow(() -> new PriceNotAvailableException("No hay precios disponibles para este producto y marca."));
    }
}
