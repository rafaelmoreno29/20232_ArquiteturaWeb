package com.example.aula3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.aula3.models.CategoriaCurso;
import com.example.aula3.repository.CategoriaCursoRepository;
import com.example.aula3.models.Curso;
import com.example.aula3.models.Pessoa;
import com.example.aula3.models.Usuario;
import com.example.aula3.repository.CursoRepository;
import com.example.aula3.repository.PessoaRepository;
import com.example.aula3.repository.UsuarioRepository;
import com.example.aula3.security.JwtService;

@SpringBootApplication
public class Aula3Application {

	@Bean
	public CommandLineRunner init(
			@Autowired CursoRepository cursoRepository,
			@Autowired CategoriaCursoRepository categoriaCursoRepository,
			@Autowired PessoaRepository pessoaRepository,
			@Autowired UsuarioRepository usuarioRepository) {
		return args -> {
			cursoRepository.save(
					new Curso((long) 0, "teste01", 2000));
			cursoRepository.save(
					new Curso((long) 0, "teste02", 2050));
			List<Curso> listaCursos = cursoRepository.findAll();
			listaCursos.forEach(System.out::println);

			System.out.println("** Exemplo obter por nome **");
			listaCursos = cursoRepository.findByNomeLike("%2%");
			listaCursos.forEach(System.out::println);

			System.out.println("** Exemplo inserir categoria **");
			CategoriaCurso c1 = new CategoriaCurso(null, "TI");
			categoriaCursoRepository.save(c1);

			System.out.println("** Exemplo atualiza categ. curso **");
			listaCursos.get(0).setCategoriaCurso(c1);
			cursoRepository.save(listaCursos.get(0));

			// System.out.println("** Exemplo LAZY **");
			// List<CategoriaCurso> categs = categoriaCursoRepository.findAll();
			// for (CategoriaCurso ca : categs) {
			// System.out.println(ca.getId() + " - " + ca.getNome() + "qtde cursos: " +
			// ca.getCursos().size());
			// }
			System.out.println("** Exemplo FETCH **");
			CategoriaCurso cc = categoriaCursoRepository
					.findCategoriaCursoFetchCursos((long) 1);
			System.out.println(cc.getCursos().size());

			System.out.println("** INSERIR PESSOA **");
			Pessoa p = new Pessoa(0L, "Rafael", null);
			pessoaRepository.save(p);

			System.out.println("** BUSCAR PESSOA E CURSO **");
			Curso curso = cursoRepository.findById(1L).orElseThrow();
			Pessoa pessoa = pessoaRepository.findById(1L).orElseThrow();

			System.out.println("** INSERIR PESSOA E CURSO **");
			// curso.getPessoas().add(pessoa);
			pessoa.getCursos().add(curso);
			// cursoRepository.save(curso);
			pessoaRepository.save(pessoa);

		};
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext contexto = SpringApplication.run(Aula3Application.class);
		JwtService service = contexto.getBean(JwtService.class);
		UsuarioRepository usuarioRepository = contexto.getBean(UsuarioRepository.class);
		PasswordEncoder passwordEncoder = contexto.getBean(PasswordEncoder.class);

		Usuario usuario = new Usuario(0, "Rafael", "admin", passwordEncoder.encode("123"),
				"Administrador");
		String token = service.gerarToken(usuario);
		System.out.println(token);
		boolean isValid = service.validarToken(token);
		System.out.println("Token válido? " + isValid);
		System.out.println("Usuário: " + service.obterLoginUsuario(token));

		usuarioRepository.save(usuario);
	}

}
