package com.example.aula3.dtos;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrorDTO {
    @Getter
    private List<String> errors;

    public ApiErrorDTO(String mensagem) {
        errors = Arrays.asList(mensagem);
    }

    public ApiErrorDTO(List<String> erros) {
        this.errors = erros;
    }

}
