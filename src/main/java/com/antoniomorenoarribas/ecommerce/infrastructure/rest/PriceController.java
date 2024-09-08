package com.antoniomorenoarribas.ecommerce.infrastructure.rest;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antoniomorenoarribas.ecommerce.application.dto.requestDTO.PriceRequestDTO;
import com.antoniomorenoarribas.ecommerce.application.dto.responseDTO.ErrorResponseDTO;
import com.antoniomorenoarribas.ecommerce.application.dto.responseDTO.PriceResponseDTO;
import com.antoniomorenoarribas.ecommerce.application.service.PriceService;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private PriceService priceService;
    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);
    
    @Autowired
    public PriceController(PriceService priceService) {
		this.priceService = priceService;
	}
    @PostMapping("/find")
    public ResponseEntity<?> getFinalPrice(@Valid @RequestBody PriceRequestDTO request) {
        // Añadir información contextual al MDC
        String requestId = MDC.get("requestId"); // Asumiendo que el MDCFilter añade esto
        MDC.put("productId", request.getProductId().toString());
        MDC.put("brandId", request.getBrandId().toString());

        logger.info("Iniciando solicitud con requestId: {}, ProductId: {}, BrandId: {}, Fecha de Aplicación: {}",
                requestId, request.getProductId(), request.getBrandId(), request.getApplicationDate());

        long startTime = System.currentTimeMillis(); // Medición del tiempo de procesamiento

        try {
            // Obtener el resultado del servicio
            PriceResponseDTO priceResponse = priceService.getFinalPrice(request);

            if (priceResponse != null) {
                logger.info("Solicitud con requestId: {} procesada exitosamente en {} ms. Precio final: {}",
                        requestId, System.currentTimeMillis() - startTime, priceResponse.getPrice());
                return ResponseEntity.ok(priceResponse); // HTTP 200 OK con el DTO de respuesta
            } else {
                logger.warn("No se encontraron precios para requestId: {} (ProductId: {}, BrandId: {})",
                        requestId, request.getProductId(), request.getBrandId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDTO("PRICE_NOT_FOUND", 
                              "No se encontraron precios para el producto y la marca especificados."));
            }

        } catch (Exception e) {
            logger.error("Error procesando solicitud con requestId: {}. Detalles del error: {}", requestId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO("500", "Error interno del servidor."));
        } finally {
            // Limpiar el MDC después de la ejecución
            MDC.clear();
        }
    }
}

    
