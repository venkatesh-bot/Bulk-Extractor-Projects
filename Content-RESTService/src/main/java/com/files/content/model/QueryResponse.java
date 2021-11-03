package com.files.content.model;

import lombok.Data;

@Data
public class QueryResponse {
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipcode;
}
