package org.todtete.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/** Conversión de objetos a/desde JSON usando Jackson. */
public final class JsonUtil {

    // ObjectMapper es costoso de crear y es thread-safe: se reutiliza una sola instancia
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtil() {
    }

    public static String aJson(Object objeto) {
        try {
            return MAPPER.writeValueAsString(objeto);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("No se pudo convertir a JSON", e);
        }
    }

    public static <T> T desdeJson(String json, Class<T> tipo) {
        try {
            return MAPPER.readValue(json, tipo);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("JSON inválido", e);
        }
    }
}
