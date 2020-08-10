package com.xpanse.www.urlshorten.controller;

import com.xpanse.www.urlshorten.model.UrlShortenRequest;
import com.xpanse.www.urlshorten.model.UrlShortenResponse;
import com.xpanse.www.urlshorten.service.UrlHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Api(tags = "urlShortenService", value = "URL Shorten Service - REST API")
@RestController
@RequestMapping("/")
public class UrlController {

    private final UrlHandler handler;

    @Autowired
    public UrlController(UrlHandler handler) {
        this.handler = handler;
    }

    @ApiOperation(value = "Creates the short URL for the requested long URL",
            httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Short URL is created successfully"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(value = "/url/shorten",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UrlShortenResponse> createShortUrl(@RequestBody final UrlShortenRequest request) {
        final UrlShortenResponse response = handler.createShortUrl(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Redirects the short URL to the expected long URL", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Short URL is successfully redirected"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/id={shortUrlId}")
    public ResponseEntity<Void> getLongUrl(@PathVariable final String shortUrlId) {
        final String longUrl = handler.getLongUrl(shortUrlId);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }
}
