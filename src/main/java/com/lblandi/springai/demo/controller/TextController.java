package com.lblandi.springai.demo.controller;

import com.lblandi.springai.demo.request.AiTaskRequest;
import com.lblandi.springai.demo.response.ApiResponse;
import com.lblandi.springai.demo.service.TextOperationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/texts")
public class TextController {
    private final TextOperationService service;

    public TextController(TextOperationService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Creates a new task related to a text operation", method = "POST", operationId = "createTask")
    public ResponseEntity<ApiResponse<String>> createTask(
            @RequestBody AiTaskRequest taskRequest) {
        var result = service.execute(taskRequest.text(), taskRequest.operation());
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
