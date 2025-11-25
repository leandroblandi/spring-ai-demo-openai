package com.lblandi.springai.demo.helper;

public final class PromptsHelper {
    private static final String BASE_INSTRUCTIONS = "Return only the direct result with no explanations, disclaimers, " +
            "metadata, or formatting. Do not include emojis.";

    public static final String SUMMARIZE_PROMPT = "Summarize the following text in one concise paragraph: %s. "
            + BASE_INSTRUCTIONS;

    public static final String ANALYZE_PROMPT = "Provide a focused analysis of the following text, covering its main idea and intent: %s. "
            + BASE_INSTRUCTIONS;

    public static final String EXPLAIN_PROMPT = "Explain the meaning of the following text in clear and simple terms: %s. "
                    + BASE_INSTRUCTIONS;

    public static final String EXPAND_PROMPT = "Expand the following text by adding relevant context while keeping the core idea intact: %s. "
                    + BASE_INSTRUCTIONS;

    private PromptsHelper() {}
}
