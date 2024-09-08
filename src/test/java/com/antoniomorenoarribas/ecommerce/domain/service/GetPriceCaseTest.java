package com.antoniomorenoarribas.ecommerce.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.antoniomorenoarribas.ecommerce.domain.model.Price;

class GetPriceCaseTest {
	
    private GetPriceCase getPriceCase;

    @BeforeEach
    void setUp() {
        getPriceCase = new GetPriceCase();  // Inicializamos el servicio del dominio
    }
    
    @Test
    void shouldReturnPriceWithHighestPriority() {
        // Given
        List<Price> prices = new ArrayList<>();
        prices.add(createPrice(new BigDecimal("25.45"), 0));
        prices.add(createPrice(new BigDecimal("30.50"), 1));  // Mayor prioridad

        // When
        Price result = getPriceCase.getFinalPrice(prices);

        // Then
        assertNotNull(result);
        assertEquals(new BigDecimal("30.50"), result.getPrice());  // El precio con mayor prioridad es 30.50
    }
    
    @Test
    void shouldThrowExceptionWhenNoPricesAvailable() {
        // Given
        List<Price> emptyPrices = new ArrayList<>();

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            getPriceCase.getFinalPrice(emptyPrices);
        });

        assertEquals("No price available for this product and brand", exception.getMessage());
    }
    
    @Test
    void shouldReturnAnyPriceWhenAllHaveSamePriority() {
        // Given
        List<Price> prices = new ArrayList<>();
        prices.add(createPrice(new BigDecimal("25.45"), 1));
        prices.add(createPrice(new BigDecimal("30.50"), 1));  // Misma prioridad

        // When
        Price result = getPriceCase.getFinalPrice(prices);

        // Then
        assertNotNull(result);
        // Como ambos precios tienen la misma prioridad, el servicio puede devolver cualquiera de los dos
        assertTrue(result.getPrice().equals(new BigDecimal("25.45")) || 
                   result.getPrice().equals(new BigDecimal("30.50")));
    }

    // Método auxiliar para crear objetos Price
    private Price createPrice(BigDecimal priceValue, int priority) {
        Price price = new Price();
        price.setPrice(priceValue);
        price.setPriority(priority);
        return price;
    }
}
