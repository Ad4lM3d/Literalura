package com.alura.Literalura.Modelos;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Autores")
public class Autor {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Libro> libros;


    public Autor(){

    }

    public Autor(ResultadosAutores resultadosAutores){
        this.name = resultadosAutores.nombres();
        this.birthYear = resultadosAutores.nacimiento();
        this.deathYear = resultadosAutores.muerte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }


    @Override
    public String toString() {

        return "Nombre: " + name +
                "\n~ Año de nacimiento: " + birthYear +
                "\n~ Año de fallecimiento: " + deathYear +
                "\n~ Libros: " + libros +
                "\n~ id: " + id + "\n";

    }
}
