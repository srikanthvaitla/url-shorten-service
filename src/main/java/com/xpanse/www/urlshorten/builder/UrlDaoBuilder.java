package com.xpanse.www.urlshorten.builder;

import com.xpanse.www.urlshorten.dao.UrlDao;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Builder class for DAO objects.
 */
@Component
public class UrlDaoBuilder {

    /**
     * Build UrlDao object.
     *
     * @param longUrl Long URL
     * @return UrlDao object
     */
    public UrlDao buildUrlDao(String longUrl) {
        final UrlDao dao = new UrlDao();
        dao.setLongUrl(longUrl);
        dao.setCreatedDate(new Date());
        return dao;
    }
}
