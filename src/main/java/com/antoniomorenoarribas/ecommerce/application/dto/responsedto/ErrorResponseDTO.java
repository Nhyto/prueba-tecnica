package com.antoniomorenoarribas.ecommerce.application.dto.responsedto;

import java.util.List;

public class ErrorResponseDTO {
    private String errorCode;
    private String errorMessage;
    private List<String> errors;

    public ErrorResponseDTO(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    
	public ErrorResponseDTO(String errorCode, String errorMessage, List<String> errors) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errors = errors;
	}


	// Getters y Setters
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


	public List<String> getErrors() {
		return errors;
	}


	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
    
    
}
