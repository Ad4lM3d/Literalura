package com.alura.Literalura.Servicio;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            if (json == null || json.isEmpty()) {
                throw new IllegalArgumentException("El JSON recibido está vacío o nulo.");
            }
            return objectMapper.readValue(json, clase);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar el JSON recibido.", e);
        }
    }
}
