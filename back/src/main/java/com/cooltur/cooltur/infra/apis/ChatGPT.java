package com.cooltur.cooltur.infra.apis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChatGPT {

    @Value("${chatgpt.api}")
    private String ChatGPTAPI;

    public String getChatGPTAPI() {
        return ChatGPTAPI;
    }
}
