package com.antoniomorenoarribas.ecommerce.application.exceptions;

public class UnexpectedPriceProcessingException extends RuntimeException {
  
	private static final long serialVersionUID = 1L;

	public UnexpectedPriceProcessingException(String message) {
        super(message);
    }

    public UnexpectedPriceProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
