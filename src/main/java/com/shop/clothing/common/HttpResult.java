package com.shop.clothing.common;

public class HttpResult<T> extends Result<T> {
    private int code;

    private HttpResult(int code, T data, String error) {
        super(data, error);
        this.code = code;
    }
}