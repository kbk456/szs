package com.example.szskimbokyun.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;

import java.util.HashMap;
import java.util.Map;

public final class HttpHeaderUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpHeaderUtil.class);

    private HttpHeaderUtil() {}

    public static HttpHeaders setHeaderAuth(String authKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (authKey != null) {
            headers.set("Authorization", authKey); // 인증 키 설정
        }
        return headers;
    }

    public static HttpHeaders setHeaderBasicAuthorization(String clientId, String clientSecret) {
        Map<String, String> headerMap = new HashMap<>();
        logger.info(clientId);
        headerMap.put(
            "Authorization",
            String.format("%s %s", "Basic", Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes()))
        );
        headerMap.put("Accept", MediaType.APPLICATION_JSON_VALUE);
        headerMap.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return setHeaders(headerMap);
    }

    public static HttpHeaders setHeaderBasicAuthorizationForm(String clientId, String clientSecret) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(
            "Authorization",
            String.format("%s %s", "Basic", Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes()))
        );
        headerMap.put("Accept", MediaType.APPLICATION_JSON_VALUE);
        headerMap.put("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        return setHeaders(headerMap);
    }

    public static HttpHeaders setHeaderAuthorization(String accessToken) {
        return setHeaderAuthorization(accessToken, false);
    }

    public static HttpHeaders setHeaderAuthorization(String accessToken, boolean isMultipartForm) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", String.format("%s %s", "Bearer", accessToken));
        headerMap.put("Accept", MediaType.APPLICATION_JSON_VALUE);
        if (isMultipartForm) headerMap.put("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE); else headerMap.put(
            "Content-Type",
            MediaType.APPLICATION_JSON_VALUE
        );
        return setHeaders(headerMap);
    }

    public static HttpHeaders setHeaderForJson() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Accept", MediaType.APPLICATION_JSON_VALUE);
        headerMap.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return setHeaders(headerMap);
    }

    public static HttpHeaders setHeaders(Map<String, String> headerMap) {
        HttpHeaders headers = new HttpHeaders();
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            headers.set(entry.getKey(), entry.getValue());
            logger.debug("Headers:" + headers.toString());
        }
        return headers;
    }

    public static HttpHeaders setHeadersForm() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }
}
