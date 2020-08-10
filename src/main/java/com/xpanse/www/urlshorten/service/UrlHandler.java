package com.xpanse.www.urlshorten.service;

import com.xpanse.www.urlshorten.builder.UrlShortenBuilder;
import com.xpanse.www.urlshorten.exception.UrlExceptionHandler;
import com.xpanse.www.urlshorten.model.UrlShortenRequest;
import com.xpanse.www.urlshorten.model.UrlShortenResponse;
import com.xpanse.www.urlshorten.util.Constants;
import com.xpanse.www.urlshorten.validation.UrlShortenValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * URL Handler class is used to handle following.
 * 1. Validate the request.
 * 2. Handover the request to appropriate service class.
 * 3. If required, invoke multiple services (This point is not applicable to current requirement).
 */
@Component
public class UrlHandler {

    private static final Logger LOGGER = LogManager.getLogger(UrlExceptionHandler.class);

    private final UrlShortenValidator validator;

    private final UrlService service;

    private final UrlShortenBuilder builder;

    @Autowired
    public UrlHandler(UrlShortenValidator validator, UrlService service, UrlShortenBuilder builder) {
        this.validator = validator;
        this.service = service;
        this.builder = builder;
    }

    public UrlShortenResponse createShortUrl(UrlShortenRequest request) {
        logAndSetThreadContextData(request);
        validator.validateRequest(request);
        final String shortUrl = service.createShortUrl(request.getLongUrl());
        final UrlShortenResponse response = builder.buildSuccessResponse(shortUrl);
        LOGGER.info("Converted shortURL=" + response.getShortUrl() + " and guid=" + request.getTransactionGuid());
        return response;
    }

    public String getLongUrl(String shortUrlId) {
        LOGGER.info("Received shortUrlId=" + shortUrlId);
        validator.validateShortUrl(shortUrlId);
        final String longURL = service.getLongUrl(shortUrlId);
        LOGGER.info("Retrieved longURL=" + longURL);
        return longURL;
    }

    /**
     * Log the incoming request and save the guid to ThreadContext.
     * Hence, it will available for thread through out the request process and specially for GlobalExceptionHandler.
     *
     * @param request URL Shorten Request
     */
    private void logAndSetThreadContextData(UrlShortenRequest request) {
        final String guid = request.getTransactionGuid();
        LOGGER.info("Received longURL=" + request.getLongUrl() + " and guid=" + guid);
        ThreadContext.put(Constants.TRANSACTION_GUID, guid);
    }
}
