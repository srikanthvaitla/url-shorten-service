package com.xpanse.www.urlshorten.exception;

import com.xpanse.www.urlshorten.builder.UrlShortenBuilder;
import com.xpanse.www.urlshorten.model.UrlShortenResponse;
import com.xpanse.www.urlshorten.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UrlExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(UrlExceptionHandler.class);

    private final UrlShortenBuilder builder;

    @Autowired
    public UrlExceptionHandler(UrlShortenBuilder builder) {
        this.builder = builder;
    }

    @ExceptionHandler(value = UrlValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UrlShortenResponse handleException(UrlValidationException ex) {
        final String guid = ThreadContext.get(Constants.TRANSACTION_GUID);
        LOGGER.error("ValidationException occurred for guid=" + guid + " and errorMessage=" + ex.getMessage());
        return builder.buildFailureResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = UrlDaoException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public UrlShortenResponse handleException(UrlDaoException ex) {
        final String guid = ThreadContext.get(Constants.TRANSACTION_GUID);
        LOGGER.error("UrlDaoException occurred for guid=" + guid + " and errorMessage=" + ex.getMessage());
        return builder.buildFailureResponse(HttpStatus.OK, ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public UrlShortenResponse handleException(Exception ex) {
        final String guid = ThreadContext.get(Constants.TRANSACTION_GUID);
        LOGGER.error("Exception occurred for guid=" + guid + " and errorMessage=" + ex.getMessage());
        return builder.buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
