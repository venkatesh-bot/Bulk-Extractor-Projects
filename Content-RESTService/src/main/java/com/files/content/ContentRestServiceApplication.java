package com.files.content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContentRestServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentRestServiceApplication.class);

    public static void main(String[] args) {
        LOGGER.info("::::: Content-RESTService Application :::::");
        SpringApplication.run(ContentRestServiceApplication.class, args);
    }

}
