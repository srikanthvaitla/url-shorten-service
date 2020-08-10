package com.xpanse.www.urlshorten.service;

import com.xpanse.www.urlshorten.builder.UrlDaoBuilder;
import com.xpanse.www.urlshorten.dao.UrlDao;
import com.xpanse.www.urlshorten.exception.UrlDaoException;
import com.xpanse.www.urlshorten.repository.UrlRepository;
import com.xpanse.www.urlshorten.util.UrlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlDaoBuilder daoBuilder;

    private final UrlRepository urlRepository;

    private final UrlConverter conversion;

    @Autowired
    public UrlService(UrlDaoBuilder daoBuilder,
                      UrlRepository urlRepository,
                      UrlConverter conversion) {
        this.daoBuilder = daoBuilder;
        this.urlRepository = urlRepository;
        this.conversion = conversion;
    }

    /**
     * 1. Persist the long URL in to database.
     * 2. Creates shortURL using long URL DB entity id and return it.
     *
     * @param longUrl Long URL
     * @return converted short URL
     */
    public String createShortUrl(String longUrl) {
        final UrlDao dao = urlRepository.save(daoBuilder.buildUrlDao(longUrl));
        return conversion.getShortUrl(dao.getId());
    }

    /**
     * 1. Convert the short URL id to DB entity id.
     * 2. Look up DB for supplied entity id and return the matched long URL if any.
     *
     * @param shortUrlId Short URL Id
     * @return Matched long URL
     */
    public String getLongUrl(String shortUrlId) {
        final Long id = conversion.getShortUrlId(shortUrlId);
        final UrlDao dao = urlRepository.findById(id)
                .orElseThrow(() -> new UrlDaoException("LongURL is not available for the requested shortURL="
                        + conversion.getShortUrl(id)));
        return dao.getLongUrl();
    }
}
