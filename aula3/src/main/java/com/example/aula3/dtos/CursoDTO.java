package com.example.aula3.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoDTO {
    private Long id;
    private String nome;
    private int cargaHoraria;
    private int categoriaCursoId;
}
