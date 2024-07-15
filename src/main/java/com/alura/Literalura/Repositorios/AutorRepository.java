package com.alura.Literalura.Repositorios;

import com.alura.Literalura.Modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

    @Query("SELECT a FROM Autor a WHERE a.deathYear >= :fechaDeath OR a.deathYear IS NULL")
    List<Autor> autoresVivos(Integer fechaDeath);
}
