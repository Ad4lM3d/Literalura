package com.alura.Literalura.Servicio;


import com.alura.Literalura.Modelos.Autor;
import com.alura.Literalura.Modelos.Libro;
import com.alura.Literalura.Modelos.ResultadosLibros;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SLibros {

    public Libro checkAutorRepetido(ResultadosLibros datosLibro, List<Autor> autores){
        Libro libroNuevo = new Libro(datosLibro);
        Optional<Autor> autorDuplicado = autores.stream()
                .filter(a -> a.getName().equalsIgnoreCase(libroNuevo.getAutor().getName()))
                .findFirst();
        if(autorDuplicado.isPresent()){
            libroNuevo.setAutor(autorDuplicado.get());
            return libroNuevo;
        } else{
            return libroNuevo;
        }
    }
}


