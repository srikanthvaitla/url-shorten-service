package com.xpanse.www.urlshorten.exception;

import javax.persistence.PersistenceException;

public class UrlDaoException extends PersistenceException {

    private String errorMessage;

    public UrlDaoException(String errorMessage) {
        super(errorMessage);
    }
}
