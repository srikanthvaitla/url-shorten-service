package com.xpanse.www.urlshorten.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlConverter {

    @Value("${base.url}")
    private String baseUrl;

    /**
     * Creates the short URL using base url and DB entity id.
     *
     * @param shortUrlId DB entity id
     * @return Short URL
     */
    public String getShortUrl(Long shortUrlId) {
        return baseUrl + shortUrlId;
    }

    /**
     * Coverts short URL id from String format to Long format for DB look up.
     *
     * @param shortUrlId Short URL id in String format
     * @return Short URL id in Long format.
     */
    public Long getShortUrlId(String shortUrlId) {
        return Long.parseLong(shortUrlId);
    }
}

