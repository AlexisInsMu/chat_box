package com.cooltur.cooltur.domain.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record ChatBody (
        @NotBlank
        @JsonProperty("prompt")
        String body,
        @NotBlank
        @JsonProperty("user_name")
        String userName
) { }
