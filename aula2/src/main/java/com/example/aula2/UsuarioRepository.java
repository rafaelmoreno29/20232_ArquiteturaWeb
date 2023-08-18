package com.example.aula2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.aula2.models.Usuario;

@Repository
public class UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();

    public boolean inserir(Usuario usuario) {
        usuarios.add(usuario);
        return true;
    }

    public List<Usuario> obterTodos() {
        return usuarios;
    }
}
