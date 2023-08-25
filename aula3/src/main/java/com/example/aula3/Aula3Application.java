package com.example.aula3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.aula3.models.Curso;
import com.example.aula3.repository.CursoRepository;

@SpringBootApplication
public class Aula3Application {

	@Bean
	public CommandLineRunner init(@Autowired CursoRepository cursoRepository) {
		return args -> {
			cursoRepository.inserir(
					new Curso((long) 0, "teste", 2000));
			cursoRepository.inserir(
					new Curso((long) 0, "teste2", 2050));
			List<Curso> listaCursos = cursoRepository.obterTodos();
			listaCursos.forEach(System.out::println);

			System.out.println("** Exemplo obter por nome **");
			listaCursos = cursoRepository.obterPorNome("2");
			listaCursos.forEach(System.out::println);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Aula3Application.class, args);
	}

}
