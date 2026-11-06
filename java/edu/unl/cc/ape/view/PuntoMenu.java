package edu.unl.cc.ape.view;

import edu.unl.cc.ape.domain.Punto;

import java.util.Scanner;

public class PuntoMenu {
    private static final int SALIR = 0;
    private static final int MOSTRAR_PUNTOS = 1;
    private static final int CALCULAR_PENDIENTE = 2;
    private static final int MOSTRAR_TIPO_PENDIENTE = 3;
    private static final int CALCULAR_DISTANCIA = 4;
    private static final int CALCULAR_PUNTO_MEDIO = 5;
    private static final int MOSTRAR_VECTORES = 6;
    private static final int MOSTRAR_VECTORES_DESDE_ORIGEN = 7;
    private static final int CAMBIAR_PUNTOS = 8;

    private final Scanner teclado;
    private Punto primerPunto;
    private Punto segundoPunto;

    public PuntoMenu() {
        teclado = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("OPERACIONES ENTRE DOS PUNTOS");
        if (!ingresarPuntos()) {
            return;
        }

        boolean continuar = true;
        while (continuar) {
            mostrarOpciones();
            Integer opcion = leerOpcion();
            if (opcion == null) {
                break;
            }
            continuar = ejecutarOpcion(opcion);
        }
        System.out.println("Programa finalizado.");
    }

    private void mostrarOpciones() {
        System.out.println("""

                --- MENÚ DE OPERACIONES ---
                1. Mostrar los puntos
                2. Calcular la pendiente
                3. Identificar el tipo de línea
                4. Calcular la distancia
                5. Calcular el punto medio
                6. Obtener los vectores entre los puntos
                7. Obtener los vectores desde el origen
                8. Ingresar nuevamente los puntos
                0. Salir
                """);
        System.out.print("Seleccione una opción: ");
    }

    private Integer leerOpcion() {
        String entrada = leerLinea();
        if (entrada == null) {
            return null;
        }

        try {
            return Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número entero válido.");
            return -1;
        }
    }

    private boolean ejecutarOpcion(int opcion) {
        switch (opcion) {
            case MOSTRAR_PUNTOS -> mostrarPuntos();
            case CALCULAR_PENDIENTE -> mostrarPendientes();
            case MOSTRAR_TIPO_PENDIENTE -> mostrarTiposDeLinea();
            case CALCULAR_DISTANCIA -> mostrarDistancia();
            case CALCULAR_PUNTO_MEDIO -> mostrarPuntoMedio();
            case MOSTRAR_VECTORES -> mostrarVectores();
            case MOSTRAR_VECTORES_DESDE_ORIGEN -> mostrarVectoresDesdeOrigen();
            case CAMBIAR_PUNTOS -> {
                if (!ingresarPuntos()) {
                    return false;
                }
            }
            case SALIR -> {
                return false;
            }
            default -> System.out.println("Opción no válida. Intente nuevamente.");
        }
        return true;
    }

    private boolean ingresarPuntos() {
        System.out.println("\nIngrese el primer punto:");
        primerPunto = leerPunto();
        if (primerPunto == null) {
            return false;
        }

        System.out.println("\nIngrese el segundo punto:");
        segundoPunto = leerPunto();
        return segundoPunto != null;
    }

    private Punto leerPunto() {
        while (true) {
            System.out.print("Identificador del punto: ");
            String etiqueta = leerLinea();
            if (etiqueta == null) {
                return null;
            }

            Float x = leerCoordenada("Coordenada X (-100 a 100): ");
            if (x == null) {
                return null;
            }
            Float y = leerCoordenada("Coordenada Y (-100 a 100): ");
            if (y == null) {
                return null;
            }

            try {
                Punto punto = new Punto(etiqueta.trim(), x, y);
                System.out.println(punto + " creado correctamente.");
                return punto;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + ". Ingrese nuevamente el punto.");
            }
        }
    }

    private Float leerCoordenada(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = leerLinea();
            if (entrada == null) {
                return null;
            }

            try {
                return Float.parseFloat(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un valor numérico válido.");
            }
        }
    }

    private String leerLinea() {
        return teclado.hasNextLine() ? teclado.nextLine() : null;
    }

    private void mostrarPuntos() {
        System.out.println("Primer punto: " + primerPunto);
        System.out.println("Segundo punto: " + segundoPunto);
    }

    private void mostrarPendientes() {
        System.out.printf("Pendiente de %s a %s: %s%n",
                primerPunto, segundoPunto, primerPunto.pendiente(segundoPunto));
        System.out.printf("Pendiente de %s a %s: %s%n",
                segundoPunto, primerPunto, segundoPunto.pendiente(primerPunto));
    }

    private void mostrarTiposDeLinea() {
        System.out.printf("Tipo de línea entre %s y %s: %s%n",
                primerPunto, segundoPunto, primerPunto.tipoPendiente(segundoPunto));
        System.out.printf("Tipo de línea entre %s y %s: %s%n",
                segundoPunto, primerPunto, segundoPunto.tipoPendiente(primerPunto));
    }

    private void mostrarDistancia() {
        System.out.printf("Distancia entre %s y %s: %.2f%n",
                primerPunto, segundoPunto, primerPunto.calcularDistancia(segundoPunto));
    }

    private void mostrarPuntoMedio() {
        System.out.println("Punto medio: " + primerPunto.puntoMedio(segundoPunto));
    }

    private void mostrarVectores() {
        System.out.println("Vector de " + primerPunto + " a " + segundoPunto + ": "
                + primerPunto.obtenerVector(segundoPunto));
        System.out.println("Vector de " + segundoPunto + " a " + primerPunto + ": "
                + segundoPunto.obtenerVector(primerPunto));
    }

    private void mostrarVectoresDesdeOrigen() {
        System.out.println("Vector desde el origen hasta " + primerPunto + ": "
                + primerPunto.vectorDesdePuntoOrigen());
        System.out.println("Vector desde el origen hasta " + segundoPunto + ": "
                + segundoPunto.vectorDesdePuntoOrigen());
    }
}
