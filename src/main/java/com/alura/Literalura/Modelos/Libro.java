package com.alura.Literalura.Modelos;

import jakarta.persistence.*;

import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String titulo;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private Integer descargas;

    @ManyToOne(fetch = FetchType.EAGER)
    private Autor autor;

    public Libro() {
    }

    public Libro(ResultadosLibros resultadosLibros) {
        this.titulo = resultadosLibros.titulo();
        this.idioma = Idioma.fromString(resultadosLibros.lenguajes().get(0));
        this.descargas = resultadosLibros.descargas();
        Optional<Autor> autorOptional = resultadosLibros.autores().stream().map(Autor::new).findFirst();
        Optional<ResultadosAutores> autores = resultadosLibros.autores().stream().findFirst();
        if (autores.isPresent()) {
            this.autor = new Autor(autores.get());
        } else {
            System.out.println("Autor desconocido");
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return """
                -----------------Libro---------------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Descargas: %d
                -------------------------------------
                """.formatted(this.titulo, this.autor.getName(), this.idioma, this.descargas);
    }
}

