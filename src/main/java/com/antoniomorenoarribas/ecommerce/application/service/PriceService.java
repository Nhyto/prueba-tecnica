package com.antoniomorenoarribas.ecommerce.application.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniomorenoarribas.ecommerce.application.dto.requestDTO.PriceRequestDTO;
import com.antoniomorenoarribas.ecommerce.application.dto.responseDTO.PriceResponseDTO;
import com.antoniomorenoarribas.ecommerce.application.mappers.PriceMapper;
import com.antoniomorenoarribas.ecommerce.domain.model.Price;
import com.antoniomorenoarribas.ecommerce.domain.repository.PriceRepository;
import com.antoniomorenoarribas.ecommerce.domain.service.GetPriceCase;

@Service
public class PriceService {
	
		private final PriceRepository priceRepository;
	    private final PriceMapper priceMapper;
	    private final GetPriceCase getPriceUseCase;
	    
	    private static final Logger logger = LoggerFactory.getLogger(PriceService.class);

	    @Autowired
	    public PriceService(PriceRepository priceRepository, PriceMapper priceMapper, GetPriceCase getPriceUseCase) {
	        this.priceRepository = priceRepository;
	        this.priceMapper = priceMapper;
	        this.getPriceUseCase = getPriceUseCase;
	    }

	    public PriceResponseDTO getFinalPrice(PriceRequestDTO request) {
	    	
	    	String requestId = MDC.get("requestId");
	        // Loguear la entrada al servicio
	        logger.info("Iniciando el proceso para obtener precio final para ProductId: {}, BrandId: {}, Fecha de Aplicación: {}",
	                request.getProductId(), request.getBrandId(), request.getApplicationDate());

	        long startTime = System.currentTimeMillis();

	        try {
	            // Consultar precios en el repositorio
	            List<Price> prices = priceRepository.findApplicablePrices(
	                    request.getProductId(),
	                    request.getBrandId(),
	                    request.getApplicationDate());

	            if (prices.isEmpty()) {
	                logger.warn("No se encontraron precios para requestId: {}, ProductId: {}, BrandId: {}", 
	                        requestId, request.getProductId(), request.getBrandId());
	                return null;  // Se manejará en el controlador
	            }

	            // Aplicar lógica de negocio
	            Price finalPrice = getPriceUseCase.getFinalPrice(prices);

	            logger.info("Precio final calculado en PriceService con requestId: {} es: {}", requestId, finalPrice.getPrice());

	            return priceMapper.toDTO(finalPrice);

	        } catch (Exception e) {
	            logger.error("Error inesperado en PriceService para requestId: {}. Detalles del error: {}", requestId, e.getMessage(), e);
	            throw new RuntimeException("Error inesperado durante el procesamiento de precios", e);

	        } finally {
	            long endTime = System.currentTimeMillis();
	            logger.info("Tiempo de ejecución de PriceService con requestId: {} fue de {} ms", requestId, (endTime - startTime));
	        }
	    }
}
