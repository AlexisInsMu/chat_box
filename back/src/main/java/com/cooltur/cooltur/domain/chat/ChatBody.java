package com.cooltur.cooltur.domain.chat;

import jakarta.validation.constraints.NotBlank;

public record ChatBody (
        @NotBlank
        String body
) { }
