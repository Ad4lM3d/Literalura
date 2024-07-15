package com.alura.Literalura.Servicio;

public interface IConvierteDatos {

    <T>T obtenerDatos(String json, Class<T> clase);
}
