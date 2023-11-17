package com.example.aula3.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula3.dtos.CursoDTO;
import com.example.aula3.dtos.DadosCursoDTO;
import com.example.aula3.models.Curso;
import com.example.aula3.services.CursoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/curso")
public class CursoController {
    private CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @ResponseStatus(HttpStatus.CREATED)
    public Long inserir(@Valid @RequestBody CursoDTO cursoDTO) {
        return cursoService.salvar(cursoDTO).getId();
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public List<CursoDTO> listarTodos() {
        return cursoService.listarTodos();
    }

    @GetMapping("{id}")
    public DadosCursoDTO obterPorId(@PathVariable Long id) {
        return cursoService.obterPorId(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cursoService.excluir(id);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody CursoDTO dto) {
        cursoService.editar(id, dto);
    }

}
