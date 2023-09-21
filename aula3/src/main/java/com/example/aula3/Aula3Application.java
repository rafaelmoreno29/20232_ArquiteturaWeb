package com.example.aula3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.aula3.models.CategoriaCurso;
import com.example.aula3.repository.CategoriaCursoRepository;
import com.example.aula3.models.Curso;
import com.example.aula3.repository.CursoRepository;

@SpringBootApplication
public class Aula3Application {

	@Bean
	public CommandLineRunner init(
			@Autowired CursoRepository cursoRepository,
			@Autowired CategoriaCursoRepository categoriaCursoRepository) {
		return args -> {
			cursoRepository.save(
					new Curso((long) 0, "teste", 2000));
			cursoRepository.save(
					new Curso((long) 0, "teste2", 2050));
			List<Curso> listaCursos = cursoRepository.findAll();
			listaCursos.forEach(System.out::println);

			System.out.println("** Exemplo obter por nome **");
			listaCursos = cursoRepository.findByNomeLike("%2%");
			listaCursos.forEach(System.out::println);

			System.out.println("** Exemplo inserir categoria **");
			CategoriaCurso c1 = new CategoriaCurso(0, "TI");
			categoriaCursoRepository.save(c1);

			System.out.println("** Exemplo atualiza categ. curso **");
			listaCursos.get(0).setCategoriaCurso(c1);
			cursoRepository.save(listaCursos.get(0));

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Aula3Application.class, args);
	}

}
