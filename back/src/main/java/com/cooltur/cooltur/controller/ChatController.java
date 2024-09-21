package com.cooltur.cooltur.controller;

import com.cooltur.cooltur.domain.chat.Chat;
import com.cooltur.cooltur.domain.chat.ChatBody;
import com.cooltur.cooltur.domain.chat.ChatRespuesta;
import com.cooltur.cooltur.domain.chat.ChatReturn;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private Chat chatServide;

    @PostMapping
    public ResponseEntity solicitarChat(@RequestBody @Valid ChatBody body) {
        ChatReturn chatReturn = chatServide.querry(body);
        return ResponseEntity.ok(chatReturn);
    }

}
