package com.antoniomorenoarribas.ecommerce.application.exceptions;

public abstract class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final String errorCode;

	    protected ApplicationException(String errorCode, String message) {
	        super(message);
	        this.errorCode = errorCode;
	    }

	    protected ApplicationException(String errorCode, String message, Throwable cause) {
	        super(message, cause);
	        this.errorCode = errorCode;
	    }

	    public String getErrorCode() {
	        return errorCode;
	    }
	
}
