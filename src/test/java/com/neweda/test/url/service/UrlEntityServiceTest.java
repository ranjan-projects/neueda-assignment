package com.neweda.test.url.service;

import com.neweda.test.url.entity.UrlEntity;
import com.neweda.test.url.repository.UrlRepository;
import com.neweda.test.url.representation.UrlRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlEntityServiceTest {

    private static UrlRequest urlRequest;

    @Mock
    private BaseConversion baseConversion;

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlServiceImpl urlService;

    private static UrlRequest getUrlRequest() {
        UrlRequest request = new UrlRequest();
        request.setLongUrl("https://www.google.com");

        return request;
    }

    @Before
    public void setup() {
        urlRequest = getUrlRequest();
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(urlRepository, baseConversion);
    }

    @Test
    public void createShortUrlTestSuccess() {
        UrlEntity urlEntity = urlEntity();
        when(urlRepository.save(any(UrlEntity.class))).thenReturn(urlEntity);
        when(baseConversion.encode(urlEntity.getId())).thenReturn("abc");

        urlService.createShortUrl(urlRequest);

        verify(urlRepository).save(any(UrlEntity.class));
        verify(baseConversion).encode(urlEntity.getId());
    }

    @Test
    public void getLongUrlTestSuccess() {
        UrlEntity urlEntity = urlEntity();
        when(baseConversion.decode(anyString())).thenReturn(45L);
        when(urlRepository.findById(anyLong())).thenReturn(Optional.of(urlEntity));

        urlService.getLongUrl("abc");

        verify(urlRepository).findById(anyLong());
        verify(baseConversion).decode(anyString());
    }

    @Test(expected = EntityNotFoundException.class)
    public void getLongUrlTestFailure() {
        when(baseConversion.decode(anyString())).thenReturn(45L);
        when(urlRepository.findById(anyLong())).thenThrow(new EntityNotFoundException("There is no entity with abc"));

        try {
            urlService.getLongUrl("abc");
        } finally {
            verify(urlRepository).findById(anyLong());
            verify(baseConversion).decode(anyString());
        }
    }

    @Test(expected = EntityNotFoundException.class)
    public void getLongUrlTestFailureWithExpiredShortUrl() {
        UrlEntity urlEntity = expiredUrlEntity();
        when(baseConversion.decode(anyString())).thenReturn(45L);
        when(urlRepository.findById(anyLong())).thenReturn(Optional.of(urlEntity));

        try {
            urlService.getLongUrl("abc");
        } finally {
            verify(urlRepository).findById(anyLong());
            verify(urlRepository).delete(urlEntity);
            verify(baseConversion).decode(anyString());
        }
    }

    private UrlEntity urlEntity() {
        return UrlEntity.UrlEntityBuilder.anUrl()
                        .withLongUrl("https://www.google.com")
                        .withCreatedAt(new Date())
                        .withId(45L)
                        .build();

    }

    private UrlEntity expiredUrlEntity() {
        return UrlEntity.UrlEntityBuilder.anUrl()
                        .withLongUrl("https://www.google.com")
                        .withCreatedAt(new Date())
                        .withExpireAt(Date.from(LocalDate.now().minusDays(2).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                        .withId(45L)
                        .build();

    }

}