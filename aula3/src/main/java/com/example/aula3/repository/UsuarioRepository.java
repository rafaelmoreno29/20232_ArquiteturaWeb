package com.example.aula3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aula3.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);
}
