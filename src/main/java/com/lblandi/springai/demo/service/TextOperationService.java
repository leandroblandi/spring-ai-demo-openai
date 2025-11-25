package com.lblandi.springai.demo.service;

import com.lblandi.springai.demo.request.AiOperationTypeEnum;

/**
 * @author lblandi
 * @since 2025-11-25
 */
public interface TextOperationService {
    /**
     * Executes a text operation based on the specified operation type with AI
     *
     * @param text the input text to be processed
     * @param operation the type of operation to be performed on the text
     * @return the result of the text operation as a string
     */
    String execute(String text, AiOperationTypeEnum operation);
}
