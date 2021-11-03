package com.files.content.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.files.content.exception.BadRequestException;
import com.files.content.model.QueryResponse;
import com.files.content.service.IContentService;
import com.files.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

    IContentService IContentService;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("v1/contentMetadata")
    public ResponseEntity<String> search(@RequestBody String requestJson) {
        try {
            QueryResponse response = IContentService.search(requestJson);
            return ResponseEntity.ok(objectMapper.writeValueAsString(response));
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Exception while searching content metadata!!");
        }
    }

    @Autowired
    public void setContentService(ContentService contentService) {
        this.IContentService = contentService;
    }

}
