package com.cooltur.cooltur.controller;

import com.cooltur.cooltur.domain.chat.Chat;
import com.cooltur.cooltur.domain.chat.ChatBody;
import com.cooltur.cooltur.domain.chat.ChatRespuesta;
import com.cooltur.cooltur.domain.chat.ChatReturn;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private Chat chatServide;

    @GetMapping
    public ResponseEntity solicitarChat(@RequestBody @Valid ChatBody body) {
        ChatReturn chatReturn = chatServide.querry(body);
        return ResponseEntity.ok(chatReturn);
    }

}
