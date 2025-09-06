package com.miempresa.gestion_usuarios.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "role")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(nullable = false, unique = true, length = 50)
  private String name;   // <— antes era "nombre"

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }   // <— antes getNombre()
  public void setName(String name) { this.name = name; }  // <— antes setNombre(...)
}

