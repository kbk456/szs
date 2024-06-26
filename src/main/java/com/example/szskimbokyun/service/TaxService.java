package com.example.szskimbokyun.service;

import com.example.szskimbokyun.util.HttpClientBase;
import com.example.szskimbokyun.util.HttpHeaderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.URISyntaxException;


@Service
@RequiredArgsConstructor
public class TaxService {

    private final HttpClientBase httpClientBase;

    public void scrap() throws URISyntaxException {
        String SCRAP_URL = "https://codetest-v4.3o3.co.kr/scrap";
        String AUTH_KEY = "fQL4PtoGRRgj6Q1OZG1WpQ==";

        HttpEntity<String> requestEntity = new HttpEntity<>(null, HttpHeaderUtil.setHeaderAuth(AUTH_KEY));
        URI uri = new URI(SCRAP_URL);

        ResponseEntity<String> stringResponseEntity = httpClientBase.callApi(String.valueOf(uri), HttpMethod.POST, requestEntity);
    }

}
