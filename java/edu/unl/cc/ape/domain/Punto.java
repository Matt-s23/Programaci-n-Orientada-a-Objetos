package edu.unl.cc.ape.domain;

/**
 * @author Wilman Chamba Z.
 * Represents a point in a Cartesian plane.
 */
public class Punto {
    // Atributos
    private String etiqueta;
    private float x;
    private float y;

    public Punto() {
        this("", 0, 0);
    }

    public Punto(String etiqueta, float x, float y) {
        this.etiqueta = etiqueta;
        this.x = validarRango(x, "x");
        this.y = validarRango(y, "y");
    }

    private float validarRango(float valor, String atributo) {
        if (!Float.isFinite(valor) || valor < -100 || valor > 100) {
            throw new IllegalArgumentException("El valor de " + atributo + " debe estar entre -100 y 100");
        }
        return valor;
    }

    public float pendiente(Punto puntoFinal) {
        return (puntoFinal.y - this.y) / (puntoFinal.x - this.x);
    }

    public Punto puntoMedio(Punto puntoFinal) {
        float x = (this.x + puntoFinal.getX()) / 2;
        float y = (this.y + puntoFinal.getY()) / 2;
        return new Punto("1/2", x, y);
    }

    public String tipoPendiente(Punto puntoFinal) {
        if (this.x == puntoFinal.x && this.y == puntoFinal.y) {
            return "Indeterminada";
        }
        if (this.x == puntoFinal.x) {
            return "Vertical";
        }

        float m = this.pendiente(puntoFinal);
        if (m == 0) {
            return "Horizontal";
        } else if (m > 0) {
            return "Ascendente";
        } else {
            return "Descendente";
        }
    }

    public float calcularDistancia(Punto puntoFinal) {
        return (float) Math.hypot(puntoFinal.x - this.x, puntoFinal.y - this.y);
    }

    public Punto obtenerVector(Punto puntoFinal) {
        float x = puntoFinal.getX() - this.x;
        float y = puntoFinal.getY() - this.y;
        String etiquetaVector = "VR[" + this.etiqueta + "->" + puntoFinal.getEtiqueta() + "]";
        return new Punto(etiquetaVector, x, y);
    }

    public Punto vectorDesdePuntoOrigen() {
        String etiquetaVector = "VR[O->" + this.etiqueta + "]";
        return new Punto(etiquetaVector, x, y);

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = validarRango(x, "x");
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = validarRango(y, "y");
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return "P" + this.etiqueta + " (" +
                x +
                ", " + y +
                ')';
    }
}
