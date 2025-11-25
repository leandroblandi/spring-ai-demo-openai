package com.lblandi.springai.demo.service.impl;

import com.lblandi.springai.demo.helper.PromptsHelper;
import com.lblandi.springai.demo.request.AiOperationTypeEnum;
import com.lblandi.springai.demo.service.TextOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;


@Service
public class TextOperationServiceImpl implements TextOperationService {
    private static final Logger log = LoggerFactory.getLogger(TextOperationServiceImpl.class);

    private final ChatClient chatClient;

    public TextOperationServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @Override
    public String execute(String text, AiOperationTypeEnum operation) {
        // first, we validate the parameters
        validateParameters(text, operation);

        // after that, we handle the operation based on the type
        return handleOperationByType(text, operation);
    }

    /**
     * Handles the specified text operation type and executes the corresponding action
     * (e.g., summarization, analysis, explanation, or expansion) on the provided text.
     *
     * @param text the input text to be processed; must not be null or blank
     * @param operation the type of operation to perform, represented as an instance of AiOperationTypeEnum;
     *                  must not be null
     * @return the result of the operation as a string
     * @throws IllegalArgumentException if the text is null or blank, or if the operation type is not supported
     */
    private String handleOperationByType(String text, AiOperationTypeEnum operation) {
        return switch (operation) {
            case SUMMARIZE -> summarize(text);
            case ANALYZE -> analyze(text);
            case EXPLAIN -> explain(text);
            case EXPAND -> expand(text);
        };
    }

    /**
     * Summarizes the provided text into one concise paragraph.
     *
     * @param text the input text to be summarized; must not be null or blank
     * @return the summarized version of the text as a string
     * @throws IllegalArgumentException if the text is null or blank
     */
    private String summarize(String text) {
        String prompt = PromptsHelper.SUMMARIZE_PROMPT.formatted(text);
        return askChat(prompt);
    }

    /**
     * Analyzes the provided text, focusing on its main idea and intent.
     *
     * @param text the input text to be analyzed; must not be null or blank
     * @return the analysis of the text as a string
     * @throws IllegalArgumentException if the text is null or blank
     */
    private String analyze(String text) {
        String prompt = PromptsHelper.ANALYZE_PROMPT.formatted(text);
        return askChat(prompt);
    }

    /**
     * Explains the provided text by interpreting its meaning in clear and simple terms.
     *
     * @param text the input text to be explained; must not be null or blank
     * @return the explanation of the text as a string
     * @throws IllegalArgumentException if the text is null or blank
     */
    private String explain(String text) {
        String prompt = PromptsHelper.EXPLAIN_PROMPT.formatted(text);
        return askChat(prompt);
    }

    /**
     * Expands the provided text by adding relevant context while preserving its core idea.
     *
     * @param text the input text to be expanded; must not be null or empty
     * @return the expanded text as a string
     * @throws IllegalArgumentException if the text is null or blank
     */
    private String expand(String text) {
        String prompt = PromptsHelper.EXPAND_PROMPT.formatted(text);
        return askChat(prompt);
    }

    /**
     * Sends a prompt to the chat client and returns the response content.
     *
     * @param promptText the input string to be sent to the chat client; must not be null or empty
     * @return the response content from the chat client as a string
     * @throws IllegalArgumentException if the prompt is null or blank
     */
    private String askChat(String promptText) {
        if (promptText == null || promptText.isBlank()) {
            throw new IllegalArgumentException("Prompt cannot be null or empty");
        }

        log.debug("Sending prompt to chat client: {}", promptText);
        String response = chatClient.prompt(promptText).call().content();
        return response == null ? "" : response.replaceAll("\\s+", " ");
    }

    /**
     * Validates the input parameters for a text operation.
     *
     * @param text the input text to be validated; must not be null or blank
     * @param operation the specified operation type; must not be null
     * @throws IllegalArgumentException if the text is null, blank, or the operation is null
     */
    private void validateParameters(String text, AiOperationTypeEnum operation) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }

        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }
    }
}
