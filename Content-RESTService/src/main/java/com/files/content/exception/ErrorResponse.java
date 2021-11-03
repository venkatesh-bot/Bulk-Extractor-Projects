package com.files.content.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private String status;
    private int code;
}
