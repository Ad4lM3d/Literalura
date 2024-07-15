package com.alura.Literalura;

import com.alura.Literalura.Principal.Principal;
import com.alura.Literalura.Repositorios.AutorRepository;
import com.alura.Literalura.Repositorios.LibroRepository;
import com.alura.Literalura.Servicio.SAutor;
import com.alura.Literalura.Servicio.SLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {


	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private SLibros serviceLibros;
	@Autowired
	private SAutor autorService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(libroRepository, autorRepository, autorService, serviceLibros);
		principal.muestraElMenu();

	}
}
