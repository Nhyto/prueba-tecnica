package com.antoniomorenoarribas.ecommerce.application.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.MDC;

import com.antoniomorenoarribas.ecommerce.application.dto.requestdto.PriceRequestDTO;
import com.antoniomorenoarribas.ecommerce.application.dto.responsedto.PriceResponseDTO;
import com.antoniomorenoarribas.ecommerce.application.exceptions.PriceNotFoundException;
import com.antoniomorenoarribas.ecommerce.application.exceptions.UnexpectedPriceProcessingException;
import com.antoniomorenoarribas.ecommerce.application.mappers.PriceMapper;
import com.antoniomorenoarribas.ecommerce.domain.model.Price;
import com.antoniomorenoarribas.ecommerce.domain.repository.PriceRepository;

class PriceServiceTest {
	
	@Mock
    private PriceRepository priceRepository;
	
	@Mock
	private PriceMapper priceMapper;
	
	@InjectMocks
	private PriceService priceService;
	
	@BeforeEach
    void setUp() {
		MockitoAnnotations.openMocks(this);
        MDC.put("requestId", "testRequestId");
    }
	
	@Test
    void shouldReturnPriceResponseWhenPricesFound() {
        // Given
        PriceRequestDTO requestDTO = new PriceRequestDTO(35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0));

        
        Price mockPrice = createMockPrice();  // Creamos el objeto mock de Price
        when(priceRepository.findApplicablePrices(
            anyLong(), anyLong(), any(LocalDateTime.class)))
            .thenReturn(mockPrice);  // Simulamos que el repositorio devuelve un Optional con el Price
        
 
        

        PriceResponseDTO mockResponseDTO = new PriceResponseDTO(35455L, 1L, 1, LocalDateTime.of(2020, 6, 14, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59), new BigDecimal("35.50"), "EUR");
        
        // Mockear el mapeador
        when(priceMapper.toDTO(mockPrice)).thenReturn(mockResponseDTO);

        // When
        PriceResponseDTO response = priceService.getFinalPrice(requestDTO);

        // Then
        assertNotNull(response);
        assertEquals(mockResponseDTO.getPrice(), response.getPrice());
        verify(priceRepository).findApplicablePrices(anyLong(), anyLong(), any(LocalDateTime.class));
        verify(priceMapper).toDTO(mockPrice);
    }
	
	 @Test
	    void shouldThrowPriceNotFoundExceptionWhenNoPricesFound() {
	        // Given
	        PriceRequestDTO requestDTO = new PriceRequestDTO(35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0));

	        

	        // When & Then
	        assertThrows(UnexpectedPriceProcessingException.class, () -> {
	            priceService.getFinalPrice(requestDTO);
	        });

	        verify(priceRepository).findApplicablePrices(anyLong(), anyLong(), any(LocalDateTime.class));
	        verify(priceMapper, never()).toDTO(any());
	    }
	
	@Test
    void shouldLogAndThrowExceptionWhenUnexpectedErrorOccurs() {
        // Given
        PriceRequestDTO requestDTO = new PriceRequestDTO(35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0));

        when(priceRepository.findApplicablePrices(anyLong(), anyLong(), any(LocalDateTime.class)))
            .thenThrow(new RuntimeException("Unexpected error"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            priceService.getFinalPrice(requestDTO);
        });

        assertEquals("Error inesperado durante el procesamiento de precios", exception.getMessage());
        verify(priceRepository).findApplicablePrices(anyLong(), anyLong(), any(LocalDateTime.class));
    }
	
	
	 // Método auxiliar para crear un Price de prueba
    private Price createMockPrice() {
        Price price = new Price();
        price.setProductId(35455L);
        price.setBrandId(1L);
        price.setPrice(new BigDecimal("35.50"));
        price.setPriority(1);
        price.setCurrency("EUR");
        price.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
        return price;
    }

}
