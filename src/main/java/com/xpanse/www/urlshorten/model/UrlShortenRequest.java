package com.xpanse.www.urlshorten.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "URL Shorten Request object")
public class UrlShortenRequest {

    @ApiModelProperty(required = true, notes = "Transaction guid", example = "41dcc2eb-4a68-4753-b573-d2a7f68e4111")
    @JsonProperty
    private String transactionGuid;

    @ApiModelProperty(required = true, notes = "Long URL", example = "http://www.averylongurl.com/123/hello.com")
    @JsonProperty
    private String longUrl;
}
