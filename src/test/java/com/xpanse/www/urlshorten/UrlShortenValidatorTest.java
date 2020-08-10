package com.xpanse.www.urlshorten;

import com.xpanse.www.urlshorten.model.UrlShortenRequest;
import com.xpanse.www.urlshorten.validation.UrlShortenValidator;
import org.junit.Assert;
import org.junit.Test;

public class UrlShortenValidatorTest {

    private final UrlShortenValidator validator = new UrlShortenValidator();

    @Test
    public void testUrlInValidShortenRequest() {
        final UrlShortenRequest request = new UrlShortenRequest();
        try {
            validator.validateRequest(request);
            Assert.fail("Should get exception for invalid TransactionGuid");
        } catch (Exception ex) {
            Assert.assertEquals("Invalid transactionGuid", ex.getMessage());
        }

        request.setTransactionGuid("123-456");

        try {
            validator.validateRequest(request);
            Assert.fail("Should get exception for invalid Long URL");
        } catch (Exception ex) {
            Assert.assertEquals("Invalid URL", ex.getMessage());
        }
    }

    @Test
    public void testUrlValidShortenRequest() {
        try {
            validator.validateRequest(validRequest());
        } catch (Exception ex) {
            Assert.fail("Shouldn't get exception for valid request and errorMessage=" + ex.getMessage());
        }
    }

    private UrlShortenRequest validRequest() {
        final UrlShortenRequest request = new UrlShortenRequest();
        request.setLongUrl("http://www.averylongurl.com/123/hello.com");
        request.setTransactionGuid("123-456");
        return request;
    }
}
