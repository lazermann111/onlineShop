package com.skillsup.model;

public class Response<T> {
    private T data;
    private boolean success;
    private String finalMessage;

    public Response(T data, boolean success, String finalMessage) {
        this.data = data;
        this.success = success;
        this.finalMessage = finalMessage;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFinalMessage() {
        return finalMessage;
    }
}
