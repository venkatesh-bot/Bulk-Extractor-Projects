package com.files.content.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.files.content.exception.BadRequestException;
import com.files.content.model.QueryRequest;
import com.files.content.model.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContentService implements IContentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentService.class);

    ObjectMapper objectMapper = new ObjectMapper();

    public QueryResponse search(String requestJsonInString) {
        try {
            QueryRequest queryRequest = objectMapper.readValue(requestJsonInString, QueryRequest.class);
            LOGGER.info("search query request : {} ", queryRequest);
            QueryResponse response = new QueryResponse();
            if ("Venkatesh".equals(queryRequest.getName())){
                response.setName("Venkatesh");
            }
            else if ("Krishna".equals(queryRequest.getName())){
                response.setName("Krishna");
            }
            else if ("Sanjay".equals(queryRequest.getName())){
                response.setName("Sanjay");
            }
            response.setAddress("2600 E Renner Road");
            response.setCity("Richardson");
            response.setState("TX");
            response.setZipcode("75082");
            return response;
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Please check query request!!");
        }
    }
}
