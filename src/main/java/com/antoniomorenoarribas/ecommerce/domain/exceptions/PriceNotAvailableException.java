package com.antoniomorenoarribas.ecommerce.domain.exceptions;

public class PriceNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PriceNotAvailableException(String message) {
        super(message);
    }

    public PriceNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}