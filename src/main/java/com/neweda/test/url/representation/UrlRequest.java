package com.neweda.test.url.representation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "Request payload for POST Create Shorten URL")
public class UrlRequest {

    @ApiModelProperty(notes = "Long URL to shorten", required = true)
    private String longUrl;

    @ApiModelProperty(notes = "Expiry date time of url")
    private Date expiryDate;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
