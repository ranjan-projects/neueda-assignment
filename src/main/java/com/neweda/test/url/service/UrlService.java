package com.neweda.test.url.service;

import com.neweda.test.url.representation.UrlRequest;

public interface UrlService {

    String createShortUrl(UrlRequest request);

    String getLongUrl(String shortUrl);
}
