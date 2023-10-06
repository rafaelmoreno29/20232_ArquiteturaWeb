package com.example.aula3.services;

import java.util.List;

import com.example.aula3.dtos.CursoDTO;
import com.example.aula3.dtos.DadosCursoDTO;
import com.example.aula3.models.Curso;

public interface CursoService {
    Curso salvar(CursoDTO cursoDTO);

    List<CursoDTO> listarTodos();

    DadosCursoDTO obterPorId(Long id);

    void excluir(Long id);

    void editar(Long id, CursoDTO dto);

}
