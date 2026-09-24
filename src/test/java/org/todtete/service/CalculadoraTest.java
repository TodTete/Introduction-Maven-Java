package org.todtete.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias con JUnit Jupiter.
 *
 * <p>Surefire ejecuta automáticamente las clases que terminan en {@code Test}
 * durante la fase {@code test} ({@code mvn test}).
 *
 * <p>Muestra: {@code @BeforeEach}, {@code @DisplayName}, {@code @Nested},
 * pruebas parametrizadas y verificación de excepciones.
 */
@DisplayName("Calculadora")
class CalculadoraTest {

    private Calculadora calc;

    // Se ejecuta antes de CADA prueba: cada test recibe una instancia limpia
    @BeforeEach
    void setUp() {
        calc = new Calculadora();
    }

    @Test
    @DisplayName("sumar 2 + 3 = 5")
    void sumar() {
        assertEquals(5, calc.sumar(2, 3));
    }

    @Test
    void restarYMultiplicar() {
        assertEquals(1, calc.restar(3, 2));
        assertEquals(6, calc.multiplicar(3, 2));
    }

    // Prueba parametrizada: se ejecuta una vez por cada fila del CSV
    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
            "10, 2, 5.0",
            "9, 3, 3.0",
            "1, 4, 0.25"
    })
    void dividir(double a, double b, double esperado) {
        assertEquals(esperado, calc.dividir(a, b), 0.0001);
    }

    @Test
    void dividirEntreCeroLanzaExcepcion() {
        ArithmeticException ex = assertThrows(ArithmeticException.class, () -> calc.dividir(1, 0));
        assertEquals("No se puede dividir entre cero", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, -8, 100})
    void esPar(int numero) {
        assertTrue(calc.esPar(numero));
    }

    // Agrupa pruebas relacionadas
    @Nested
    @DisplayName("factorial")
    class Factorial {

        @ParameterizedTest(name = "{0}! = {1}")
        @CsvSource({"0, 1", "1, 1", "5, 120", "10, 3628800"})
        void calcula(int n, long esperado) {
            assertEquals(esperado, calc.factorial(n));
        }

        @Test
        void negativoLanzaExcepcion() {
            assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
        }
    }
}
