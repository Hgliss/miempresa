package com.miempresa.gestion_usuarios.controller;

import com.miempresa.gestion_usuarios.dto.AuthDtos.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void loginAdmin_ok() throws Exception {
        // JSON de credenciales válidas
        String json = objectMapper.writeValueAsString(
                new LoginRequest("admin@demo.com", "admin123")
        );

       mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.email").value("admin@demo.com"))
                    .andExpect(jsonPath("$.role").value("ADMIN")); 
    }

 
}
