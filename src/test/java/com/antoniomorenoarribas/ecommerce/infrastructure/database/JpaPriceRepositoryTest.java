package com.antoniomorenoarribas.ecommerce.infrastructure.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

import com.antoniomorenoarribas.ecommerce.application.mappers.PriceMapper;
import com.antoniomorenoarribas.ecommerce.domain.model.Price;
import com.antoniomorenoarribas.ecommerce.infrastructure.database.entities.BrandEntity;
import com.antoniomorenoarribas.ecommerce.infrastructure.database.entities.PriceEntity;
import com.antoniomorenoarribas.ecommerce.infrastructure.database.entities.ProductEntity;

class JpaPriceRepositoryTest {
	
	@Mock
    private SpringDataPriceRepository springDataPriceRepository;
	
	@Mock
    private PriceMapper priceMapper;
	
	@InjectMocks
    private JpaPriceRepository jpaPriceRepository;
	
	
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
    void shouldReturnMappedPricesWhenEntitiesAreFound() {
        // Given
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        // Mock de las entidades PriceEntity
        Optional<PriceEntity> mockEntityOptional = Optional.of(createMockPriceEntity());

        // Mock de la lista devuelta por SpringDataPriceRepository
        when(springDataPriceRepository.findTopByProductIdAndBrandIdAndApplicationDateOrderByPriorityDesc(
            productId, brandId, applicationDate)).thenReturn(mockEntityOptional);

        // Mock del mapeo de entidad a dominio
        Price mockPrice = createMockPrice();
        when(priceMapper.toDomain(mockEntityOptional.get())).thenReturn(mockPrice);

        // When
        Price result = jpaPriceRepository.findApplicablePrices(productId, brandId, applicationDate);

        // Then
        assertNotNull(result);
   
        assertEquals(new BigDecimal("35.50"), result.getPrice());

        verify(springDataPriceRepository).findTopByProductIdAndBrandIdAndApplicationDateOrderByPriorityDesc(productId, brandId, applicationDate);
        verify(priceMapper).toDomain(mockEntityOptional.get());
    }
	 
	 private PriceEntity createMockPriceEntity() {
		    PriceEntity entity = new PriceEntity();
		    entity.setProduct(createMockProductEntity());  // Asignar la entidad Product
		    entity.setBrand(createMockBrandEntity());      // Asignar la entidad Brand
		    entity.setPrice(new BigDecimal("35.50"));
		    entity.setPriority(1);
		    entity.setCurrency("EUR");
		    entity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
		    entity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
		    return entity;
		}
	 
	 private ProductEntity createMockProductEntity() {
		    ProductEntity product = new ProductEntity();
		    product.setId(35455L);
		    product.setName("Test Product");
		    return product;
	 }
	 
	 private BrandEntity createMockBrandEntity() {
		    BrandEntity brand = new BrandEntity();
		    brand.setId(1L);
		    brand.setName("Test Brand");
		    return brand;
	 }
	 
	 private Price createMockPrice() {
		    Price price = new Price();
		    price.setProductId(35455L);   // Usamos setProductId en lugar de setProduct
		    price.setBrandId(1L);         // Usamos setBrandId en lugar de setBrand
		    price.setPrice(new BigDecimal("35.50"));
		    price.setPriority(1);
		    price.setCurrency("EUR");
		    price.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
		    price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
		    return price;
		}

}
