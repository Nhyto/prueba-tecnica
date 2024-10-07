package com.antoniomorenoarribas.ecommerce.infrastructure.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.antoniomorenoarribas.ecommerce.application.dto.responsedto.ErrorResponseDTO;
import com.antoniomorenoarribas.ecommerce.application.exceptions.PriceNotFoundException;
import com.antoniomorenoarribas.ecommerce.application.exceptions.UnexpectedPriceProcessingException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlePriceNotFoundException(PriceNotFoundException ex) {
        logger.warn("Price not found: {}", ex.getMessage());
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("PRICE_NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnexpectedPriceProcessingException.class)
    public ResponseEntity<ErrorResponseDTO> handlePriceProcessingException(UnexpectedPriceProcessingException ex) {
        logger.error("Price processing error: {}", ex.getMessage(), ex);
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("PRICE_PROCESSING_ERROR", "Ocurrió un error procesando el precio.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneralException(Exception ex) {
        logger.error("Unhandled exception: {}", ex.getMessage(), ex);
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("INTERNAL_SERVER_ERROR", "Ocurrió un error inesperado.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponseDTO> handleBindException(BindException ex) {
        List<String> bindingErrors = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());

        logger.warn("Binding error: {}", bindingErrors);
        
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("INVALID_INPUT", "Error de conversión o entrada inválida", bindingErrors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
