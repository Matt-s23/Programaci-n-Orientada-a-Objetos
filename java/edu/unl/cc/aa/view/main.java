package edu.unl.cc.aa.view;

import edu.unl.cc.aa.domain.PositiveNumberCalculator;

/**
 * Ejercicio: Clean Code
 * @author Matias Calva G.
 */
public class main {

    public static void main(String[] args) {
        PositiveNumberCalculator calculator = new PositiveNumberCalculator();
        MessagePrinter printer = new MessagePrinter();

        try {
            int result = calculator.sum(5, 3);
            printer.print("Resultado: " + result);
            calculator.sum(-2, 3);
        } catch (IllegalArgumentException e) {
            printer.print(e.getMessage());
        }
    }
}