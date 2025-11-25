package com.lblandi.springai.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AiTaskRequest(
        @JsonProperty("text") String text,
        @JsonProperty("operation") AiOperationTypeEnum operation) {
}
