package com.antoniomorenoarribas.ecommerce.infrastructure.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antoniomorenoarribas.ecommerce.application.dto.requestdto.PriceRequestDTO;
import com.antoniomorenoarribas.ecommerce.application.dto.responsedto.PriceResponseDTO;
import com.antoniomorenoarribas.ecommerce.application.service.PriceService;

@RestController
@RequestMapping("v1/prices")
public class PriceController {

    private PriceService priceService;
    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);
    
    @Autowired
    public PriceController(PriceService priceService) {
		this.priceService = priceService;
	}
    @GetMapping
    public ResponseEntity<PriceResponseDTO> getFinalPrice(@Valid @ModelAttribute PriceRequestDTO requestDTO) {
      
        String requestId = MDC.get("requestId");
        MDC.put("productId", requestDTO.getProductId().toString());
        MDC.put("brandId",requestDTO.getBrandId().toString());

        logger.info("Iniciando solicitud con requestId: {}, ProductId: {}, BrandId: {}, Fecha de Aplicación: {}",
                requestId, requestDTO.getProductId(), requestDTO.getBrandId(), requestDTO.getApplicationDate());

        long startTime = System.currentTimeMillis();

        try {
        	
            PriceResponseDTO priceResponse = priceService.getFinalPrice(requestDTO);
            logger.info("Solicitud con requestId: {} procesada exitosamente en {} ms. Precio final: {}",
                    requestId, System.currentTimeMillis() - startTime, priceResponse.getPrice());
            return ResponseEntity.ok(priceResponse);
        } finally {
            MDC.clear();
        }
    }
}

    
