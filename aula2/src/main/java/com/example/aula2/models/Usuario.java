package com.example.aula2.models;

public class Usuario {
    private Integer codigo;
    private String nome;

    public Usuario(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Usuario() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
