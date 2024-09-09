package com.antoniomorenoarribas.ecommerce.application.exceptions;

public class PriceNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	// Constructor que acepta solo un mensaje
    public PriceNotFoundException(String message) {
        super(message);
    }

    // Constructor que acepta un mensaje y una causa (opcional)
    public PriceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

