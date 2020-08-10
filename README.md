# url-shorten-service
URL Shorten service is a Spring boot REST API and deals with shorten the URL 
i.e. provides the capability of converting the long URL to the short URL and redirect to expected long URL for the requested short url.

Service has two following two api's:
* Receive and persist the long URL in H2 in memory data base. Creates the short URL for the requested long URL and return the response back as JSON.
* Receive the short URL and look up to the data base and redirects the short URL to the expected long URL. 

Prerequisites
=============
* Java 11
* Maven 2.3.2+

Build and Deploy
=================
    mvn clean package

    mvn spring-boot:run

API Details
===========

Swagger Endpoint:

    localhost:8080/swagger-ui.html

Url Shorten Endpoint:

    http://localhost:8080/url/shorten

Request Sample:

    {
      "longUrl": "http://www.averylongurl.com/123/hello.com",
      "transactionGuid": "41dcc2eb-4a68-4753-b573-d2a7f68e4111"
    }
    
Response Sample:
    
 - Success response:
    
        {
          "status": "OK",
          "shortUrl": "http://localhost:8080/id=1"
        }
    
 - Failure response:

        {
          "status": "BAD_REQUEST",
          "errorMessage": "Invalid URL"
        }

Features
===========

* Database:

    - Used H2 in memory database for in this project to store the longUrl.
    - We use in memory option by using following parameter.
        spring.datasource.url=jdbc:h2:mem:jpadb
    - Hence, for every time we start the application, we loose the data. 
    - To override, we can use the file option.

* Swagger API.
* Global Exception Handling.
* Tracking each request using  transaction guid. 
* Logging.
* Junit Test cases added for UrlService class and UrlShortenValidator.


