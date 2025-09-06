package com.miempresa.gestion_usuarios.config;

import com.miempresa.gestion_usuarios.entity.Role;
import com.miempresa.gestion_usuarios.entity.User;
import com.miempresa.gestion_usuarios.repository.RoleRepository;
import com.miempresa.gestion_usuarios.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(RoleRepository roles, UserRepository users){
        return args -> {
            // Crear rol ADMIN si no existe
            roles.findByName("ADMIN").orElseGet(() -> {
                Role r = new Role();
                r.setName("ADMIN");
                return roles.save(r);
            });

            // Crear rol USER si no existe
            roles.findByName("USER").orElseGet(() -> {
                Role r = new Role();
                r.setName("USER");
                return roles.save(r);
            });

            // Crear usuario admin por defecto si no existe
            if (users.findByEmail("admin@demo.com").isEmpty()) {
                User u = new User();
                u.setEmail("admin@demo.com");
                u.setPassword("admin123"); //  solo para demo/QA
                u.setFullName("Administrador");
                u.setRole(roles.findByName("ADMIN").get());
                users.save(u);
            }
        };
    }
}
