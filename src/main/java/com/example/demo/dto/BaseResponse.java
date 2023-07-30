package com.example.demo.dto;

public class BaseResponse {

    private String message;

    public BaseResponse(String message) {
        this.message = message;
    }

    public BaseResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
