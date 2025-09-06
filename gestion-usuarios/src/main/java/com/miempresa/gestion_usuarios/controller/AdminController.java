package com.miempresa.gestion_usuarios.controller;

import com.miempresa.gestion_usuarios.dto.AuthDtos.UserResponse;
import com.miempresa.gestion_usuarios.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

  private final AuthService auth;

  public AdminController(AuthService auth) {
    this.auth = auth;
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserResponse>> all() {
    return ResponseEntity.ok(auth.listUsers());
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    auth.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
