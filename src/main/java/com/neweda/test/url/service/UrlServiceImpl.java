package com.neweda.test.url.service;

import com.neweda.test.url.entity.UrlEntity;
import com.neweda.test.url.repository.UrlRepository;
import com.neweda.test.url.representation.UrlRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final BaseConversion baseConversion;

    public UrlServiceImpl(UrlRepository urlRepository, BaseConversion baseConversion) {
        this.urlRepository = urlRepository;
        this.baseConversion = baseConversion;
    }

    @Override
    public String createShortUrl(UrlRequest request) {
        UrlEntity url = UrlEntity.UrlEntityBuilder.anUrl()
                        .withLongUrl(request.getLongUrl())
                        .withCreatedAt(new Date())
                        .withExpireAt(request.getExpiryDate())
                        .build();
        UrlEntity urlEntity = urlRepository.save(url);

        return baseConversion.encode(urlEntity.getId());
    }

    @Override
    public String getLongUrl(String shortUrl) {
        Long id = baseConversion.decode(shortUrl);
        UrlEntity entity = urlRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        if (entity.getExpireAt() != null && entity.getExpireAt().before(new Date())) {
            urlRepository.delete(entity);
            throw new EntityNotFoundException("Short URL is already expired");
        }

        return entity.getLongUrl();
    }
}
