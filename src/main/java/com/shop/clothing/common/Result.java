package com.shop.clothing.common;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class Result<T>{
    private T data;
    private String error;
    protected Result(T data, String error) {
        this.data = data;
        this.error = error;
    }
    public static <T> Result<T> success(T data) {
        return new Result<T>(data, null);
    }
    public static <T> Result<T> error(String error) {
        return new Result<T>(null, error);
    }
    public boolean isSuccess() {
        return this.error == null;
    }
}
