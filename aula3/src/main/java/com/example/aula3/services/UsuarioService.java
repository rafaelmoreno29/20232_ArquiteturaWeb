package com.example.aula3.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.aula3.dtos.UsuarioDTO;
import com.example.aula3.models.Usuario;

public interface UsuarioService {
    Usuario salvar(UsuarioDTO dto);

    UsuarioDTO obterUsuarioPorId(Integer id);

    List<UsuarioDTO> obterUsuarios();

    UserDetails autenticar(Usuario usuario);
}
