package com.content.model;

import lombok.Data;

@Data
public class QueryRequest {
    private String name;
    private String city;
    private String state;
    private String zipcode;
}
