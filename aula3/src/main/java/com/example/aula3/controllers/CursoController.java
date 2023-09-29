package com.example.aula3.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula3.dtos.CursoDTO;
import com.example.aula3.models.Curso;
import com.example.aula3.services.CursoService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/curso")
public class CursoController {
    private CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long inserir(@RequestBody CursoDTO cursoDTO) {
        return cursoService.salvar(cursoDTO).getId();
    }

    @GetMapping
    public List<CursoDTO> listarTodos() {
        return cursoService.listarTodos();
    }
}
