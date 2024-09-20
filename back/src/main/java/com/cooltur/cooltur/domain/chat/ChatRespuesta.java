package com.cooltur.cooltur.domain.chat;

import okhttp3.ResponseBody;

public record ChatRespuesta (String body) {
    public ChatRespuesta(String body) {
        this.body = body;
    }
}
