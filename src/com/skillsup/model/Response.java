package com.skillsup.model;

public class Response<T> {
    private T data;
    private boolean success;
    private String errorMessage;

    public Response(T data, boolean success, String errorMessage) {
        this.data = data;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
