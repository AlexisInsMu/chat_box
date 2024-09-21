package com.cooltur.cooltur.infra.exceptions;

public class InvalidChatMessageError extends RuntimeException {
    public InvalidChatMessageError(String s) {
        super(s);
    }
}
