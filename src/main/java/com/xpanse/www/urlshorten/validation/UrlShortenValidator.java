package com.xpanse.www.urlshorten.validation;

import com.xpanse.www.urlshorten.exception.UrlValidationException;
import com.xpanse.www.urlshorten.model.UrlShortenRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

@Component
public class UrlShortenValidator {

    private final UrlValidator validator = UrlValidator.getInstance();

    /**
     * Validate incoming request based on following criteria. This can be changed as per requirements.
     * 1. TransactionGuid should present and used to track the request process.
     * 2. LongURL should valid URL.
     *
     * @param request URL Shorten Request
     */
    public void validateRequest(UrlShortenRequest request) {
        if (StringUtils.isEmpty(request.getTransactionGuid())) {
            throw new UrlValidationException("Invalid transactionGuid");
        }
        if (!validator.isValid(request.getLongUrl())) {
            throw new UrlValidationException("Invalid URL");
        }
    }

    /**
     * Validate the short URL id base on following criteria.
     * 1. Short URL id should be numeric number.
     *
     * @param shortUrlId Short URL id in String format
     */
    public void validateShortUrl(String shortUrlId) {
        if (!StringUtils.isNumeric(shortUrlId)) {
            throw new UrlValidationException("Invalid short url");
        }
    }
}
