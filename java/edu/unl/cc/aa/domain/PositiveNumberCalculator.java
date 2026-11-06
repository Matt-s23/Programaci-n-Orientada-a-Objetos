package edu.unl.cc.aa.domain;

/**
 * Ejercicio: Clean Code
 * @author Matias Calva G.
 */
public class PositiveNumberCalculator {

    public int sum(int firstNumber, int secondNumber) {
        if (firstNumber <= 0 || secondNumber <= 0) {
            throw new IllegalArgumentException("Solo se permiten números positivos");
        }
        return firstNumber + secondNumber;
    }
}