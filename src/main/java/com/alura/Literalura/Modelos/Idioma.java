package com.alura.Literalura.Modelos;

public enum Idioma {

    INGLES  ("en", "Inglés"),
    ESPANOL ("es", "Espanol" ),
    FRANCES ("fr", "Francés"),
    PORTUGUES("pt", "Portugués");

    private String idiomaDefecto;
    private String idiomaEspanol;

    Idioma(String idiomaDefecto, String idiomaEspanol){
        this.idiomaDefecto = idiomaDefecto;
        this.idiomaEspanol = idiomaEspanol;
    }

    public static Idioma fromString(String text){
        for(Idioma idioma : Idioma.values()){
            if(idioma.idiomaDefecto.equalsIgnoreCase(text)){
                return idioma;
            }
        }throw new IllegalArgumentException("Ningun libro encontrado"+ text);

    }

    public static Idioma fromEspanol(String text){
        for(Idioma idioma : Idioma.values()){
            if(idioma.idiomaEspanol.equalsIgnoreCase(text)){
                return idioma;
            }
        }throw new IllegalArgumentException("Ningun libro encontrado"+ text);

    }



}
