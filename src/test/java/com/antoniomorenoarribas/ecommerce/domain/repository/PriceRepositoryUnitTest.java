package com.antoniomorenoarribas.ecommerce.domain.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
	 void shouldReturnPriceForValidProductBrandAndDate() {
	        // Given
	        Long productId = 35455L;
	        Long brandId = 1L;
	        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

	        when(priceRepository.findApplicablePrices(productId, brandId, applicationDate))
	            .thenReturn(createMockPrice(productId, brandId, new BigDecimal("35.50"), 0));

	        // When
	        Price prices = priceRepository.findApplicablePrices(productId, brandId, applicationDate);

	        // Then
	        assertEquals(new BigDecimal("35.50"), prices.getPrice());
	  }
	 
	 @Test
	    void shouldReturnNullObjectWhenNoPricesAvailable() {
	        // Given
	        Long productId = 99999L;  // Producto no existente
	        Long brandId = 1L;
	        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

	        when(priceRepository.findApplicablePrices(productId, brandId, applicationDate))
	            .thenReturn(new Price());  // Simula que no hay precios disponibles

	        // When
	        Price prices = priceRepository.findApplicablePrices(productId, brandId, applicationDate);

	        // Then
	        assertNull(prices.getPrice());
	    }

	 
	// Método auxiliar para crear objetos Price
	    private Price createMockPrice(Long productId, Long brandId, BigDecimal priceValue, int priority) {
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
