package com.antoniomorenoarribas.ecommerce.application.exceptions;

public class InvalidPriceRequestException extends ApplicationException {
   
	private static final long serialVersionUID = 1L;

	public InvalidPriceRequestException(String message) {
        super("INVALID_REQUEST", message);
    }
}
