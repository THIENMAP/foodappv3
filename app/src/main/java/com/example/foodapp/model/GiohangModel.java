package com.example.foodapp.model;

import java.util.List;

public class GiohangModel {

    boolean success;
    String message;
    List<Giohang> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Giohang> getResult() {
        return result;
    }

    public void setResult(List<Giohang> result) {
        this.result = result;
    }
}
