package com.example.aula3.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.aula3.dtos.CategoriaCursoDTO;
import com.example.aula3.dtos.CursoDTO;
import com.example.aula3.dtos.DadosCursoDTO;
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
                List<CursoDTO> cursos = cursoRepository.findAll().stream().map(
                                (Curso c) -> {
                                        return CursoDTO.builder()
                                                        .id(c.getId())
                                                        .nome(c.getNome())
                                                        .cargaHoraria(c.getCargaHoraria())
                                                        .categoriaCursoId(
                                                                        c.getCategoriaCurso() == null ? 0
                                                                                        : c.getCategoriaCurso().getId())
                                                        .build();
                                }).collect(Collectors.toList());
                return cursos;
        }

        @Override
        public DadosCursoDTO obterPorId(Long id) {
                return cursoRepository.findById(id).map((Curso c) -> {
                        return DadosCursoDTO.builder()
                                        .id(c.getId())
                                        .nome(c.getNome())
                                        .cargaHoraria(c.getCargaHoraria())
                                        .categoria(c.getCategoriaCurso() != null ? CategoriaCursoDTO.builder()
                                                        .id(c.getCategoriaCurso().getId())
                                                        .nome(c.getCategoriaCurso().getNome())
                                                        .build() : null)
                                        .build();
                })
                                .orElseThrow(
                                                () -> new RegraNegocioException("Id do curso não encontrado"));
        }

        @Override
        public void excluir(Long id) {
                cursoRepository.deleteById(id);
        }

        @Override
        public void editar(Long id, CursoDTO dto) {
                Curso curso = cursoRepository.findById(id)
                                .orElseThrow(() -> new RegraNegocioException("Curso não encontrado."));

                CategoriaCurso categoriaCurso = categoriaCursoRepository.findById(dto.getCategoriaCursoId())
                                .orElseThrow(() -> new RegraNegocioException("Categoria não encontrada."));

                curso.setNome(dto.getNome());
                curso.setCargaHoraria(dto.getCargaHoraria());
                curso.setCategoriaCurso(categoriaCurso);
                cursoRepository.save(curso);
        }

}
