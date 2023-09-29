package com.example.aula3.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.aula3.dtos.CursoDTO;
import com.example.aula3.exceptions.RegraNegocioException;
import com.example.aula3.models.CategoriaCurso;
import com.example.aula3.models.Curso;
import com.example.aula3.repository.CategoriaCursoRepository;
import com.example.aula3.repository.CursoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final CategoriaCursoRepository categoriaCursoRepository;

    @Override
    public Curso salvar(CursoDTO cursoDTO) {
        CategoriaCurso categ = categoriaCursoRepository.findById(
                cursoDTO.getCategoriaCursoId()).orElseThrow(
                        () -> new RegraNegocioException("Código da categoria não encontrado!"));

        Curso c = new Curso();
        c.setCargaHoraria(cursoDTO.getCargaHoraria());
        c.setCategoriaCurso(categ);
        c.setNome(cursoDTO.getNome());
        return cursoRepository.save(c);
    }

    public List<CursoDTO> listarTodos() {
        List<Curso> cursos = cursoRepository.findAll();
        List<CursoDTO> cursosDTO = new ArrayList();
        for (Curso c : cursos) {
            cursosDTO.add(new CursoDTO(
                    c.getId(),
                    c.getNome(),
                    c.getCargaHoraria(),
                    c.getCategoriaCurso() != null ? c.getCategoriaCurso().getId() : 0));
        }
        return cursosDTO;
    }

}
