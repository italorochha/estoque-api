package com.italo.estoque_api.dto;

public class AuthDTOs {
    public record AuthenticationDTO(String login, String senha) {}
    public record RegisterDTO(String login, String senha, String role) {}
    public record LoginResponseDTO(String token) {}
}