package org.todtete.model;

import java.util.Objects;

/**
 * Modelo inmutable de una persona (un {@code record} de Java).
 *
 * <p>El constructor compacto valida los datos al crear el objeto.
 */
public record Persona(String nombre, String email, int edad) {

    public static final int MAYORIA_DE_EDAD = 18;

    public Persona {
        Objects.requireNonNull(nombre, "El nombre es obligatorio");
        Objects.requireNonNull(email, "El email es obligatorio");
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
    }

    /** @return {@code true} si la persona tiene 18 años o más. */
    public boolean esMayorDeEdad() {
        return edad >= MAYORIA_DE_EDAD;
    }
}
