package com.stock.analysis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class Response<T> {

    private String resultCode;
    private T result;

    public static <T> Response<T> success() {
        return new Response<T>("SUCCESS", null);
    }

    public static <T> Response<T> success(T result) {
        return new Response<T>("SUCCESS", result);
    }

    public static Response<Void> error(String resultCode) {
        return new Response<Void>(resultCode, null);
    }
 }
