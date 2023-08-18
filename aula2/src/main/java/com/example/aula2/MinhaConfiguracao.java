package com.example.aula2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinhaConfiguracao {

    @Bean
    public String nomeAplicacao() {
        return "Projeto Aula 2";
    }

    @Bean
    public String versaoAplicacao() {
        return "1.0";
    }
}
