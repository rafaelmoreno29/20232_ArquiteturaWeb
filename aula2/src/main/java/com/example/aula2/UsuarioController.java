package com.example.aula2;

import org.springframework.web.bind.annotation.RestController;

import com.example.aula2.models.Usuario;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UsuarioController {
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping(value = "usuario")
    public Usuario inserir(@RequestBody Usuario usuario) {
        usuarioRepository.inserir(usuario);
        return usuario;
    }

    @GetMapping(value = "usuario")
    public List<Usuario> obterTodos() {
        return usuarioRepository.obterTodos();
    }

}
