package com.alura.Literalura.Servicio;

import com.alura.Literalura.Modelos.Autor;
import org.springframework.stereotype.Service;

@Service
public class SAutor {

    public Autor convertirNombres (Autor autor){
        try{
            autor.setName(autor.getName().split(",")[1]
                    .replace(" ", "") + " " +
                    autor.getName().split(",")[0]);
        }catch (Exception e){
        }return autor;
    }

}
