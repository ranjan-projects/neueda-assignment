package com.neweda.test.url.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "SHORT_URL")
public class UrlEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @Column(name = "LONG_URL")
    String longUrl;

    @Column(name = "CREATED_AT")
    Date createdAt;

    @Column(name = "EXPIRE_AT")
    Date expireAt;

    public Long getId() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public static final class UrlEntityBuilder {
        Long id;
        String longUrl;
        Date createdAt;
        Date expireAt;

        private UrlEntityBuilder() {
        }

        public static UrlEntityBuilder anUrl() {
            return new UrlEntityBuilder();
        }

        public UrlEntityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UrlEntityBuilder withLongUrl(String longUrl) {
            this.longUrl = longUrl;
            return this;
        }

        public UrlEntityBuilder withCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UrlEntityBuilder withExpireAt(Date expireAt) {
            this.expireAt = expireAt;
            return this;
        }

        public UrlEntity build() {
            UrlEntity url = new UrlEntity();
            url.createdAt = this.createdAt;
            url.id = this.id;
            url.longUrl = this.longUrl;
            url.expireAt = this.expireAt;
            return url;
        }
    }
}
