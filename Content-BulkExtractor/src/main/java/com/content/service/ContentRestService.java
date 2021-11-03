package com.content.service;

import com.content.model.QueryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContentRestService {

    @Value("${contentRESTServiceURL}")
    private String contentRESTServiceURL;

    final RestTemplate restTemplate = new RestTemplate();
    final HttpHeaders httpHeaders = new HttpHeaders();
    ObjectMapper objectMapper = new ObjectMapper();

    public QueryResponse query(String queryRequest) throws JsonProcessingException {
        String url = contentRESTServiceURL + "/content/v1/contentMetadata";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(queryRequest, httpHeaders), String.class);
        return objectMapper.readValue(response.getBody(), QueryResponse.class);
    }

}
