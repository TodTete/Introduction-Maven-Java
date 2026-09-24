package org.todtete.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

/**
 * Lee {@code app.properties} desde el classpath.
 *
 * <p>Los valores {@code ${project.name}} y {@code ${project.version}} de ese archivo
 * los reemplaza Maven durante la fase {@code process-resources} (filtrado de recursos).
 */
public record InfoAplicacion(String nombre, String version) {

    private static final String ARCHIVO = "/app.properties";

    public static InfoAplicacion cargar() {
        try (InputStream in = InfoAplicacion.class.getResourceAsStream(ARCHIVO)) {
            if (in == null) {
                return new InfoAplicacion("desconocido", "desconocida");
            }
            Properties props = new Properties();
            props.load(in);
            return new InfoAplicacion(props.getProperty("app.nombre"), props.getProperty("app.version"));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
