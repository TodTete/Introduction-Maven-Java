package org.todtete.service;

/** Operaciones matemáticas básicas, ideal para practicar pruebas unitarias. */
public class Calculadora {

    public int sumar(int a, int b) {
        return a + b;
    }

    public int restar(int a, int b) {
        return a - b;
    }

    public int multiplicar(int a, int b) {
        return a * b;
    }

    /**
     * @throws ArithmeticException si el divisor es cero.
     */
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir entre cero");
        }
        return a / b;
    }

    /**
     * Calcula n! de forma iterativa.
     *
     * @throws IllegalArgumentException si n es negativo.
     */
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n debe ser >= 0");
        }
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public boolean esPar(int n) {
        return n % 2 == 0;
    }
}
