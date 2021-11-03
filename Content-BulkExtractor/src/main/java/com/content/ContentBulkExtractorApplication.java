package com.content;

import com.content.extractor.ExtractorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContentBulkExtractorApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentBulkExtractorApplication.class);

    ExtractorService extractorService;

    public static void main(String[] args) {
        LOGGER.info("::::: Content-BulkExtractor :::::");
        SpringApplication.run(ContentBulkExtractorApplication.class, args);
    }

    @Autowired
    public void setExtractorService(ExtractorService extractorService) {
        this.extractorService = extractorService;
    }

}
