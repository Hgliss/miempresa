package com.miempresa.gestion_usuarios.dto;

public class AuthDtos {
  public record RegisterRequest(String email, String password, String fullName, String role) {}
  public record LoginRequest(String email, String password) {}
  public record UserResponse(Long id, String email, String fullName, String role) {}
}
