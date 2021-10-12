package com.users.management.infrastructure.response;

public class ControllerResponse<T> {

    private T data;

    public ControllerResponse() {
    }

    public ControllerResponse(final T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(final T data) {
        this.data = data;
    }
}
