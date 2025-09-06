package com.miempresa.gestion_usuarios.service;

import com.miempresa.gestion_usuarios.dto.AuthDtos.*;
import com.miempresa.gestion_usuarios.entity.Role;
import com.miempresa.gestion_usuarios.entity.User;
import com.miempresa.gestion_usuarios.repository.RoleRepository;
import com.miempresa.gestion_usuarios.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
  private final UserRepository users;
  private final RoleRepository roles;

  public AuthService(UserRepository users, RoleRepository roles) {
    this.users = users;
    this.roles = roles;
    }

  public UserResponse register(RegisterRequest r){
    if (users.existsByEmail(r.email())) {
      throw new IllegalArgumentException("Email ya está en uso");
    }
    String roleName = (r.role() == null || r.role().isBlank()) ? "USER" : r.role().toUpperCase();
    Role role = roles.findByName(roleName).orElseThrow(() -> new IllegalArgumentException("Rol no existe"));

    // Nota: para la tarea guardamos password en texto plano. En real: BCrypt.
    User u = new User();
    u.setEmail(r.email());
    u.setPassword(r.password());
    u.setFullName(r.fullName());
    u.setRole(role);

    users.save(u);
    return new UserResponse(u.getId(), u.getEmail(), u.getFullName(), u.getRole().getName());
  }

  public UserResponse login(LoginRequest r){
    User u = users.findByEmail(r.email())
        .filter(x -> x.getPassword().equals(r.password()))
        .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));

    return new UserResponse(u.getId(), u.getEmail(), u.getFullName(), u.getRole().getName());
  }

  public List<UserResponse> listUsers(){
    return users.findAll().stream()
        .map(u -> new UserResponse(u.getId(), u.getEmail(), u.getFullName(), u.getRole().getName()))
        .toList();
  }

  public void deleteUser(Long id){
    users.deleteById(id);
  }
}
