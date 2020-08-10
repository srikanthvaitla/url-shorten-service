package com.xpanse.www.urlshorten.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@ApiModel(description = "URL Shorten Response object")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlShortenResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, notes = "HttpStatus", example = "200")
    @JsonProperty
    private HttpStatus status;

    @ApiModelProperty(required = true, notes = "Short URL", example = "myservice.com/id=2")
    @JsonProperty
    private String shortUrl;

    @ApiModelProperty(notes = "Error Message", example = "Invalid Request")
    @JsonProperty
    private String errorMessage;
}
