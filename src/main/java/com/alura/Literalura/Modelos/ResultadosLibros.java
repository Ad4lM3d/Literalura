package com.alura.Literalura.Modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadosLibros(
        @JsonAlias("id") Long Id,
        @JsonAlias("title") String titulo,
        @JsonAlias("subjects") List<String> temas,
        @JsonAlias("languages")List<String> lenguajes,
        @JsonAlias("authors") List<ResultadosAutores> autores,
        @JsonAlias("dowload_count") Integer descargas
        ) {
}
