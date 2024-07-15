package com.alura.Literalura.Principal;


import com.alura.Literalura.Modelos.*;
import com.alura.Literalura.Repositorios.AutorRepository;
import com.alura.Literalura.Repositorios.LibroRepository;
import com.alura.Literalura.Servicio.ConsumoAPI;
import com.alura.Literalura.Servicio.ConvierteDatos;
import com.alura.Literalura.Servicio.SAutor;
import com.alura.Literalura.Servicio.SLibros;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository Lrepository;
    private AutorRepository Arepository;
    private SAutor autorService;
    private SLibros librosService;

    public Principal(LibroRepository Lrepository, AutorRepository Arepository, SAutor autorService, SLibros librosService) {
        this.Lrepository = Lrepository;
        this.Arepository = Arepository;
        this.autorService = autorService;
        this.librosService = librosService;
    }

    public void muestraElMenu() throws Exception {
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    1 - Buscar Libro
                    2 - Listar libros Registrados
                    3 - Listar Autores Registrados
                    4 - Listar Autores Vivos Por Año
                    5 - Listar Libros por Idioma
                    6 - Descargas de los Libros Registrados  
                    0 - Salir
                    """;
            try {
                System.out.println(menu);
                System.out.print("Digite el valor correspondiente a la acción: ");
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\nParece que digitaste algo mal.\n");
                teclado.nextLine(); // Limpiar entrada inválida
            }

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibros();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivos();
                case 5 -> listarLibrosPorIdioma();
                case 6 -> listarDescargas();
                case 0 -> System.out.println("Cerrando Aplicación...");
                default -> System.out.println("Opción Inválida");
            }
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = Arepository.findAll();
        System.out.println("\n** Mostrando los autores registrados **\n");

        for (Autor autor : autores) {
            autorService.convertirNombres(autor);
            autor.setLibros(Lrepository.buscarLibroPorAutor(autor));
        }

        autores.forEach(System.out::println);
    }

    //AÑADIR LIBROS
    private Libro getDatosLibros() {
        System.out.print("Ingrese el título del libro que desea añadir: ");
        String nombreLibro = teclado.nextLine();
        String json = consumoApi.obtenerDatos(URL_BASE +"?search="+ nombreLibro.replace(" ", "+"));
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        Optional<ResultadosLibros> resultadosLibros = datos.resultados().stream()
                .filter(d -> d.titulo().toLowerCase().contains(nombreLibro.toLowerCase()))
                .findFirst();

        Libro libroNuevo = null;
        if (resultadosLibros.isPresent()) {
            List<Autor> autores = Arepository.findAll();
            try {
                libroNuevo = librosService.checkAutorRepetido(resultadosLibros.get(), autores);
            } catch (Exception e) {
                libroNuevo = new Libro(resultadosLibros.get());
                libroNuevo.setAutor(null);
            }
        }
        return libroNuevo;
    }

    private void buscarLibroPorTitulo() throws Exception {
        Libro libroNuevo = getDatosLibros();
        if (libroNuevo != null) {
            if (libroNuevo.getAutor() != null) {
                Arepository.save(autorService.convertirNombres(libroNuevo.getAutor()));
            }
            Lrepository.save(libroNuevo);
        } else {
            System.out.println("Libro no encontrado, por favor intenta con otro");
        }

    }

    private void listarLibrosPorIdioma() {
        System.out.print("\nDigite el idioma en el que desea ver los libros (sin abreviaciones): ");
        String idioma = teclado.nextLine();
        Idioma idiomaNuevo = Idioma.fromString(idioma);

        Optional<List<Libro>> buscarPorIdioma = LibroRepository.buscarPorIdioma(idiomaNuevo);

        if (buscarPorIdioma.isPresent()) {
            List<Libro> librosPorIdioma = buscarPorIdioma.get();
            if (librosPorIdioma.isEmpty()) {
                System.out.println("\nNo tenemos libros en " + idioma + " aún! \n");
            } else {
                librosPorIdioma.forEach(System.out::println);
            }
        } else {
            System.out.println("No se encontraron libros para el idioma especificado.");
        }
    }

    private void listarAutoresVivos() {
        System.out.print("\nDigite el año límite: ");
        int limitYear;
        try {
            limitYear = teclado.nextInt();
            teclado.nextLine(); // Limpiar la entrada
        } catch (InputMismatchException e) {
            System.out.println("Dato inválido: " + e.getMessage());
            teclado.nextLine(); // Limpiar entrada inválida
            return;
        }

        List<Autor> autoresVivos = Arepository.autoresVivos(limitYear);

        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores disponibles para dicho año!\n");
        } else {
            for (Autor autor : autoresVivos) {
                if (autor.getBirthYear() <= limitYear && (autor.getDeathYear() == null || autor.getDeathYear() >= limitYear)) {
                    System.out.println(autor);
                }
            }
        }
    }

    private void listarLibros() {
        List<Libro> libros = Lrepository.findAll();
        System.out.println("\n** Mostrando libros registrados **\n");
        libros.forEach(System.out::println);
    }

    private void listarDescargas() {
        // Implementación de descargas de libros
    }
}
