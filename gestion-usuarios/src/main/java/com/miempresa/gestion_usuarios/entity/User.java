package com.miempresa.gestion_usuarios.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Email
  @NotBlank
  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @NotBlank
  @Column(nullable = false, length = 100)
  private String password;

  @NotBlank
  @Column(name = "full_name", nullable = false, length = 150)
  private String fullName;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  // getters y setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }
  public Role getRole() { return role; }
  public void setRole(Role role) { this.role = role; }

    
}
