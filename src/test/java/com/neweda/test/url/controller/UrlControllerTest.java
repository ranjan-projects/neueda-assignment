package com.neweda.test.url.controller;

import com.neweda.test.url.representation.UrlRequest;
import com.neweda.test.url.service.UrlService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlControllerTest {

    private static final String SHORT_URL = "abc";
    private static UrlRequest urlRequest;
    @InjectMocks
    private UrlController controller;

    @Mock
    private UrlService urlService;

    private static UrlRequest getUrlRequest() {
        UrlRequest request = new UrlRequest();
        request.setLongUrl("https://www.google.com");
        request.setExpiryDate(Date.from(LocalDate.now().plusDays(10).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        return request;
    }

    @Before
    public void setup() {
        urlRequest = getUrlRequest();
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(urlService);
    }

    @Test
    public void testCreateShortUrlSuccess() {
        controller.createShortenUrl(urlRequest);
        verify(urlService).createShortUrl(urlRequest);
    }

    @Test
    public void testGetAndRedirectSuccess() {
        when(urlService.getLongUrl(SHORT_URL)).thenReturn("https://www.google.com");
        controller.getAndRedirect(SHORT_URL);
        verify(urlService).getLongUrl(SHORT_URL);
    }
}
