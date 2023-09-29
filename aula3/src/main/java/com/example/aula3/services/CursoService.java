package com.example.aula3.services;

import java.util.List;

import com.example.aula3.dtos.CursoDTO;
import com.example.aula3.models.Curso;

public interface CursoService {
    Curso salvar(CursoDTO cursoDTO);

    List<CursoDTO> listarTodos();
}
