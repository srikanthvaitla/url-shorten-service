package com.xpanse.www.urlshorten.builder;

import com.xpanse.www.urlshorten.model.UrlShortenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Builder class for UrlShorten Response object.
 */
@Component
public class UrlShortenBuilder {

    /**
     * Build UrlShorten Success Response.
     *
     * @param shortUrl Shorten URL
     * @return Url Shorten Response
     */
    public UrlShortenResponse buildSuccessResponse(String shortUrl) {
        final UrlShortenResponse response = new UrlShortenResponse();
        response.setShortUrl(shortUrl);
        response.setStatus(HttpStatus.OK);
        return response;
    }

    /**
     * Build UrlShorten Failure Response.
     *
     * @param status       HttpStatus code
     * @param errorMessage error message
     * @return Url Shorten Response
     */
    public UrlShortenResponse buildFailureResponse(HttpStatus status, String errorMessage) {
        final UrlShortenResponse response = new UrlShortenResponse();
        response.setStatus(status);
        response.setErrorMessage(errorMessage);
        return response;
    }
}
