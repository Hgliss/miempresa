package com.miempresa.gestion_usuarios.service;

import com.miempresa.gestion_usuarios.dto.AuthDtos.LoginRequest;
import com.miempresa.gestion_usuarios.dto.AuthDtos.RegisterRequest;
import com.miempresa.gestion_usuarios.dto.AuthDtos.UserResponse;
import com.miempresa.gestion_usuarios.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  // cada test se revierte al terminar (limpia datos)
class AuthServiceTest {

  @Autowired AuthService auth;
  @Autowired UserRepository users;

  @BeforeEach
  void seedUser() {
    // garantiza que exista al menos 1 user para login_ok
    users.findByEmail("user1@demo.com")
        .orElseGet(() -> {
          UserResponse u = auth.register(
              new RegisterRequest("user1@demo.com","123456","Usuario Uno","USER")
          );
          return users.findById(u.id()).orElseThrow();
        });
  }

  @Test
  void login_ok() {
    var resp = auth.login(new LoginRequest("user1@demo.com","123456"));
    assertEquals("user1@demo.com", resp.email());
    assertEquals("USER", resp.role());
  }

  @Test
  void login_invalido_lanza_excepcion() {
    var ex = assertThrows(IllegalArgumentException.class,
        () -> auth.login(new LoginRequest("user1@demo.com","clave-incorrecta")));
    assertEquals("Credenciales inválidas", ex.getMessage());
  }

  @Test
  void registro_duplicado_falla() {
    // ya existe user1@demo.com
    var ex = assertThrows(IllegalArgumentException.class,
        () -> auth.register(new RegisterRequest("user1@demo.com","otra","Otro","USER")));
    assertTrue(ex.getMessage().toLowerCase().contains("email"));
  }

  @Test
  void listar_y_eliminar_usuario() {
    // registrar otro
    var nuevo = auth.register(new RegisterRequest("user2@demo.com","123456","Usuario Dos","USER"));
    // aparece en lista
    assertTrue(auth.listUsers().stream().anyMatch(u -> u.email().equals("user2@demo.com")));
    // eliminar
    auth.deleteUser(nuevo.id());
    // ya no debe estar
    assertFalse(auth.listUsers().stream().anyMatch(u -> u.email().equals("user2@demo.com")));
  }
}
