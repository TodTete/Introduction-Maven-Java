package org.todtete.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.todtete.model.Persona;
import org.todtete.repository.PersonaRepositoryEnMemoria;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/** Pruebas de las clases de utilidades y del repositorio en memoria. */
class UtilTest {

    @Test
    void capitalizaPalabras() {
        assertThat(TextoUtil.capitalizarPalabras("hola MUNDO  maven")).isEqualTo("Hola Mundo Maven");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void textoVacio(String texto) {
        assertThat(TextoUtil.capitalizarPalabras(texto)).isEmpty();
        assertThat(TextoUtil.esPalindromo(texto)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"reconocer", "Anita lava la tina", "oso"})
    void detectaPalindromos(String texto) {
        assertThat(TextoUtil.esPalindromo(texto)).isTrue();
    }

    @Test
    void noEsPalindromo() {
        assertThat(TextoUtil.esPalindromo("maven")).isFalse();
    }

    @Test
    void convierteAJsonYDeVuelta() {
        Persona ana = new Persona("Ana", "ana@correo.com", 28);

        String json = JsonUtil.aJson(ana);

        assertThat(json).contains("\"nombre\":\"Ana\"", "\"edad\":28");
        assertThat(JsonUtil.desdeJson(json, Persona.class)).isEqualTo(ana);
    }

    @Test
    void jsonInvalido() {
        assertThatThrownBy(() -> JsonUtil.desdeJson("{no es json", Persona.class))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void leeInfoFiltradaPorMaven() {
        InfoAplicacion info = InfoAplicacion.cargar();

        // Si el filtrado funciona, ya no queda el texto literal "${project.version}"
        assertThat(info.version()).isNotBlank().doesNotContain("${");
        assertThat(info.nombre()).isEqualTo("Introduction Maven");
    }

    @Test
    void repositorioEnMemoria() {
        PersonaRepositoryEnMemoria repo = new PersonaRepositoryEnMemoria();
        Persona ana = new Persona("Ana", "ana@correo.com", 28);

        repo.guardar(ana);

        assertThat(repo.buscarPorEmail("ana@correo.com")).contains(ana);
        assertThat(repo.buscarPorEmail("otro@correo.com")).isEmpty();
        assertThat(repo.listar()).containsExactly(ana);
    }
}
