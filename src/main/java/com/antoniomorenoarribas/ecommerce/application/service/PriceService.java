package com.antoniomorenoarribas.ecommerce.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniomorenoarribas.ecommerce.application.dto.requestdto.PriceRequestDTO;
import com.antoniomorenoarribas.ecommerce.application.dto.responsedto.PriceResponseDTO;
import com.antoniomorenoarribas.ecommerce.application.exceptions.PriceNotFoundException;
import com.antoniomorenoarribas.ecommerce.application.exceptions.UnexpectedPriceProcessingException;
import com.antoniomorenoarribas.ecommerce.application.mappers.PriceMapper;
import com.antoniomorenoarribas.ecommerce.domain.model.Price;
import com.antoniomorenoarribas.ecommerce.domain.repository.PriceRepository;

/**
 * Servicio que maneja la lógica para obtener el precio final de un producto para una marca inditex específica.
 * Este servicio interactúa con el repositorio de precios, aplica la lógica de negocio, y gestiona excepciones.
 */
@Service
public class PriceService {
	
		private final PriceRepository priceRepository;
	    private final PriceMapper priceMapper;
	    
	    private static final Logger logger = LoggerFactory.getLogger(PriceService.class);

	    @Autowired
	    public PriceService(PriceRepository priceRepository, PriceMapper priceMapper) {
	        this.priceRepository = priceRepository;
	        this.priceMapper = priceMapper;
	    }

	    /**
	     * Obtiene el precio final de un producto dado su ID de producto, de la marca y la fecha de aplicación( o consulta).
	     * Si no se encuentra ningún precio, lanza una excepción {@link PriceNotFoundException}.
	     * En caso de error inesperado, lanza una excepción {@link UnexpectedPriceProcessingException}.
	     *
	     * @param request El DTO que contiene los detalles de la solicitud (productId, brandId, y applicationDate).
	     * @return Un {@link PriceResponseDTO} que contiene el precio final, si se encuentra.
	     * @throws PriceNotFoundException Si no se encuentran precios para el producto y la marca especificados.
	     * @throws UnexpectedPriceProcessingException Si ocurre un error inesperado durante el procesamiento.
	     */
	    public PriceResponseDTO getFinalPrice(PriceRequestDTO request) {
	    	
	    	String requestId = MDC.get("requestId");
	        
	        logger.info("Iniciando el proceso para obtener precio final para ProductId: {}, BrandId: {}, Fecha de Aplicación: {}",
	                request.getProductId(), request.getBrandId(), request.getApplicationDate());

	        long startTime = System.currentTimeMillis();

	        try {
	            Price price = priceRepository.findApplicablePrices(
	                    request.getProductId(),
	                    request.getBrandId(),
	                    request.getApplicationDate());

	            logger.info("Precio final calculado en PriceService con requestId: {} es: {}", requestId, price.getPrice());

	            return priceMapper.toDTO(price);

	        } catch (PriceNotFoundException e) {
	            logger.warn("PriceNotFoundException para requestId: {}: {}", requestId, e.getMessage());
	            throw e;
	            
	        } catch (Exception e) {
	            logger.error("Error inesperado en PriceService para requestId: {}. Detalles del error: {}", requestId, e.getMessage(), e);
	            throw new UnexpectedPriceProcessingException("Error inesperado durante el procesamiento de precios", e);

	        } finally {
	            long endTime = System.currentTimeMillis();
	            logger.info("Tiempo de ejecución de PriceService con requestId: {} fue de {} ms", requestId, (endTime - startTime));
	        }
	    }
}
