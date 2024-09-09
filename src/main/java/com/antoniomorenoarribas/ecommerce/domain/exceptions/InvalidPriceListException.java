package com.antoniomorenoarribas.ecommerce.domain.exceptions;

public class InvalidPriceListException extends RuntimeException {

    public InvalidPriceListException(String message) {
        super(message);
    }

    public InvalidPriceListException(String message, Throwable cause) {
        super(message, cause);
    }
}
