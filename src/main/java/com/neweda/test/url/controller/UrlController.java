package com.neweda.test.url.controller;

import com.neweda.test.url.representation.UrlRequest;
import com.neweda.test.url.service.UrlService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @ApiOperation(value = "Create short URL", notes = "Convert long url into short url")
    @PostMapping("/create/short")
    public String createShortenUrl(@RequestBody UrlRequest request) {
        return urlService.createShortUrl(request);
    }

    @ApiOperation(value = "Get Long URL", notes = "Get long url from short url and redirect")
    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        String url = urlService.getLongUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create(url))
                        .build();
    }
}
