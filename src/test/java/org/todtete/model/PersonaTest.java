package org.todtete.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/** Pruebas del record {@link Persona} usando aserciones de AssertJ. */
class PersonaTest {

    @Test
    void creaPersonaValida() {
        Persona p = new Persona("Ana", "ana@correo.com", 18);

        assertThat(p.nombre()).isEqualTo("Ana");
        assertThat(p.esMayorDeEdad()).isTrue();
    }

    @Test
    void menorDeEdad() {
        assertThat(new Persona("Luis", "luis@correo.com", 17).esMayorDeEdad()).isFalse();
    }

    @Test
    void validaDatos() {
        assertThatThrownBy(() -> new Persona(" ", "a@b.com", 20))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Persona("Ana", "sin-arroba", 20))
                .hasMessageContaining("Email inválido");
        assertThatThrownBy(() -> new Persona("Ana", "a@b.com", -1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Persona(null, "a@b.com", 1))
                .isInstanceOf(NullPointerException.class);
    }
}
