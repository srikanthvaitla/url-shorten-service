package com.xpanse.www.urlshorten;

import com.xpanse.www.urlshorten.builder.UrlDaoBuilder;
import com.xpanse.www.urlshorten.dao.UrlDao;
import com.xpanse.www.urlshorten.repository.UrlRepository;
import com.xpanse.www.urlshorten.service.UrlService;
import com.xpanse.www.urlshorten.util.UrlConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

    private static final String longUrl = "http://www.averylongurl.com/123/hello.com";
    private static final String shortUrl = "http://localhost:8080/id=1";

    @Mock
    UrlDaoBuilder builder;

    @Mock
    UrlRepository repository;

    @Mock
    UrlConverter converter;

    @InjectMocks
    UrlService urlService;

    @Test
    public void createShortUrl() {
        final UrlDao dao = build();

        when(builder.buildUrlDao(anyString())).thenReturn(dao);
        when(repository.save(any(UrlDao.class))).thenReturn(dao);
        when(converter.getShortUrl(anyLong())).thenReturn(shortUrl);

        Assert.assertEquals(shortUrl, urlService.createShortUrl(longUrl));
    }

    @Test
    public void getLongUrl() {
        final UrlDao dao = build();

        when(converter.getShortUrlId(anyString())).thenReturn(1L);
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.of(dao));

        Assert.assertEquals(longUrl, urlService.getLongUrl("1"));

    }

    private UrlDao build() {
        final UrlDao dao = new UrlDao();
        dao.setLongUrl(longUrl);
        dao.setCreatedDate(new Date());
        dao.setId(1);
        return dao;
    }
}

