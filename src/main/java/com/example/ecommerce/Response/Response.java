package com.example.ecommerce.Response;

public class Response<T> {
    private T data;
    private boolean result;
    private Integer errorCode;
    private String errorMessage;
    public Response(T data) {
        this.data = data;
        result = true;
    }

    public Response(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        result = false;
    }

    public T getData() {
        return data;
    }

    public boolean isResult() {
        return result;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

