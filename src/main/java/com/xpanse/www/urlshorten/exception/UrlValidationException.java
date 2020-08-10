package com.xpanse.www.urlshorten.exception;

public class UrlValidationException extends RuntimeException {

    private String errorMessage;

    public UrlValidationException(String errorMessage) {
        super(errorMessage);
    }

}
