package com.example.szskimbokyun.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Component
public class HttpClientBase {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientBase.class);
    private RestTemplate restTemplate;

    public HttpClientBase() {
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    public ResponseEntity<String> callApi(String apiUrl, HttpMethod httpMethod, HttpEntity<?> requestEntity) {
        logger.debug("API URL: {}", apiUrl);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(apiUrl, httpMethod, requestEntity, String.class);
            logger.debug("Response Status: {}", response.getStatusCode());
            logger.debug("Response Body: {}", response.getBody());
        } catch (HttpClientErrorException e) {
            logger.debug("Response Status: {}", e.getStatusCode());
            logger.debug("ClientError: {}", new String(e.getResponseBodyAsByteArray(), StandardCharsets.UTF_8));
        } catch (HttpServerErrorException e) {
            logger.debug("ServerError: {}", e.getResponseBodyAsString());
        } catch (Exception e) {
            logger.debug("{}, {}", e, e.getMessage());
        }
        return response;
    }
}
