package org.todtete.util;

import org.apache.commons.lang3.StringUtils;

/** Utilidades de texto apoyadas en Apache Commons Lang ({@link StringUtils}). */
public final class TextoUtil {

    private TextoUtil() {
        // Clase de utilidades: no se instancia
    }

    /** "hola mundo" -> "Hola Mundo". Devuelve "" si el texto es nulo o vacío. */
    public static String capitalizarPalabras(String texto) {
        if (StringUtils.isBlank(texto)) {
            return "";
        }
        String[] palabras = StringUtils.split(texto.toLowerCase());
        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = StringUtils.capitalize(palabras[i]);
        }
        return String.join(" ", palabras);
    }

    /** Indica si un texto se lee igual al derecho y al revés (ignora espacios y mayúsculas). */
    public static boolean esPalindromo(String texto) {
        if (StringUtils.isBlank(texto)) {
            return false;
        }
        String limpio = StringUtils.deleteWhitespace(texto).toLowerCase();
        return limpio.equals(StringUtils.reverse(limpio));
    }
}
