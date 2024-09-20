package com.cooltur.cooltur.infra;

import com.cooltur.cooltur.infra.exceptions.InvalidChatMessageError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        List<ExceptionDataValidation> errores = e.getFieldErrors()
                .stream().map(ExceptionDataValidation::new)
                .toList();
        return ResponseEntity.badRequest()
                .body(errores);
    }

    @ExceptionHandler(InvalidChatMessageError.class)
    public ResponseEntity tratarError400(InvalidChatMessageError e) {
        return ResponseEntity.badRequest()
                .body(new ExceptionErrorFormater(e.getMessage()));
    }

    private record ExceptionDataValidation(String campo, String error) {
        public ExceptionDataValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    private record ExceptionErrorFormater(
            String error
    )  { }

}
