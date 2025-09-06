package com.miempresa.gestion_usuarios.controller;

import com.miempresa.gestion_usuarios.dto.AuthDtos.LoginRequest;
import com.miempresa.gestion_usuarios.dto.AuthDtos.RegisterRequest;
import com.miempresa.gestion_usuarios.dto.AuthDtos.UserResponse;
import com.miempresa.gestion_usuarios.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService auth;

  public AuthController(AuthService auth) {
    this.auth = auth;
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest r) {
    return ResponseEntity.ok(auth.register(r));
  }

  @PostMapping("/login")
  public ResponseEntity<UserResponse> login(@RequestBody LoginRequest r) {
    return ResponseEntity.ok(auth.login(r));
  }
}
