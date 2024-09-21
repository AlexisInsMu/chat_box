package com.cooltur.cooltur.domain.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatRespuesta (
        String id,
        String object,
        long created,
        String model,
        List<Choice> choices,
        Usage usage,
        @JsonProperty("system_fingerprint") String systemFingerprint
) {
    public record Choice(
            int index,
            Message message,
            Object logprobs, // Could be null or more complex
            @JsonProperty("finish_reason") String finishReason
    ) {}


    public record Message(
            String role,
            String content,
            Object refusal
    ) {}

    private record Usage(
            @JsonProperty("prompt_tokens") int promptTokens,
            @JsonProperty("completion_tokens") int completionTokens,
            @JsonProperty("total_tokens") int totalTokens,
            @JsonProperty("completion_tokens_details") CompletionTokensDetails completionTokensDetails
    ) {}

    private record CompletionTokensDetails(
            @JsonProperty("reasoning_tokens") int reasoningTokens
    ) {}
}
