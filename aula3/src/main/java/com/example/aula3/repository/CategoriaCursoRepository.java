package com.example.aula3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.aula3.models.CategoriaCurso;

public interface CategoriaCursoRepository extends
        JpaRepository<CategoriaCurso, Integer> {
}
