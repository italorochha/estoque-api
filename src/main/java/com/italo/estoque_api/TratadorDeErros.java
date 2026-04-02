package com.italo.estoque_api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> tratarErroDeNegocio(RuntimeException ex) {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("erro", ex.getMessage());
        return ResponseEntity.badRequest().body(resposta);
    }
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarErrosDeValidacao(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->erros.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(erros);
    }
}