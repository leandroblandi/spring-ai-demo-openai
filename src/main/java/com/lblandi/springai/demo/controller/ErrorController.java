package com.lblandi.springai.demo.controller;

import com.lblandi.springai.demo.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    private static final Logger log = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.debug("Invalid request: {}", e.getMessage());
        var response = ApiResponse.failure("Found invalid values in request: " + e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
