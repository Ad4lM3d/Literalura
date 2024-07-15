package com.alura.Literalura.Repositorios;

import com.alura.Literalura.Modelos.Autor;
import com.alura.Literalura.Modelos.Idioma;
import com.alura.Literalura.Modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository <Libro, Long> {
    @Query("SELECT l FROM Libro l WHERE l.autor = :autor")
    List<Libro> buscarLibroPorAutor(Autor autor);

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    static Optional<List<Libro>> buscarPorIdioma(Idioma idioma) {
        return null;
    }
}
