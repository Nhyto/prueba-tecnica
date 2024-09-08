package com.antoniomorenoarribas.ecommerce.domain.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.antoniomorenoarribas.ecommerce.domain.model.Price;

class PriceRepositoryUnitTest {
	
	
	@Mock
    private PriceRepository priceRepository;

	 @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
    
	 @Test
	 void shouldReturnPricesForValidProductBrandAndDate() {
	        // Given
	        Long productId = 35455L;
	        Long brandId = 1L;
	        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

	        List<Price> mockPrices = new ArrayList<>();
	        mockPrices.add(createMockPrice(productId, brandId, applicationDate, new BigDecimal("35.50"), 0));

	        when(priceRepository.findApplicablePrices(productId, brandId, applicationDate))
	            .thenReturn(mockPrices);

	        // When
	        List<Price> prices = priceRepository.findApplicablePrices(productId, brandId, applicationDate);

	        // Then
	        assertFalse(prices.isEmpty());
	        assertEquals(1, prices.size());
	        assertEquals(new BigDecimal("35.50"), prices.get(0).getPrice());
	  }
	 
	 @Test
	    void shouldReturnEmptyListWhenNoPricesAvailable() {
	        // Given
	        Long productId = 99999L;  // Producto no existente
	        Long brandId = 1L;
	        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

	        when(priceRepository.findApplicablePrices(productId, brandId, applicationDate))
	            .thenReturn(new ArrayList<>());  // Simula que no hay precios disponibles

	        // When
	        List<Price> prices = priceRepository.findApplicablePrices(productId, brandId, applicationDate);

	        // Then
	        assertTrue(prices.isEmpty());
	    }

	 
	// Método auxiliar para crear objetos Price
	    private Price createMockPrice(Long productId, Long brandId, LocalDateTime applicationDate, BigDecimal priceValue, int priority) {
	        Price price = new Price();
	        price.setProductId(productId);
	        price.setBrandId(brandId);
	        price.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
	        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
	        price.setPrice(priceValue);
	        price.setPriority(priority);
	        price.setCurrency("EUR");
	        return price;
	    }
	
}
