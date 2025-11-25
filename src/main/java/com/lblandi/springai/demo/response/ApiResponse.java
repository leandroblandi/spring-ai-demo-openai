package com.lblandi.springai.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record ApiResponse <T> (
        @JsonProperty("success") Boolean success,
        @JsonProperty("data") T data,
        @JsonProperty("timestamp") LocalDateTime timestamp) {


    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> failure(T data) {
        return new ApiResponse<>(false, data, LocalDateTime.now());
    }
}
