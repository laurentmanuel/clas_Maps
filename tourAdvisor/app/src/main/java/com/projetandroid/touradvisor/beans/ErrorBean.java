package com.projetandroid.touradvisor.beans;

public class ErrorBean {
    private String message;

    public ErrorBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
