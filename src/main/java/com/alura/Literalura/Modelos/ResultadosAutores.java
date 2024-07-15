package com.alura.Literalura.Modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadosAutores(@JsonAlias("name") String nombres,
                                @JsonAlias("birth_year") Integer nacimiento,
                                @JsonAlias("death_year") Integer muerte) {
}
