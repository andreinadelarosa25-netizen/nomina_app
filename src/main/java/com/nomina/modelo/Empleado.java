package com.nomina.modelo;

/**
 * Clase base abstracta para todos los tipos de empleado.
 * Cada subclase define cómo calcula su salario bruto y sus bonos.
 */
public abstract class Empleado {

    private final String nombre;

    protected Empleado(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /** Salario antes de bonos y deducciones. */
    public abstract double calcularSalarioBruto();

    /** Bonos propios del tipo de empleado. Por defecto no tiene. */
    public double calcularBonos() {
        return 0;
    }

    /** Indica si es empleado permanente (recibe bono de alimentación). */
    public boolean esPermanente() {
        return false;
    }

    /** Indica si tiene acceso al fondo de ahorro. Por defecto no. */
    public boolean accedeAlFondoAhorro() {
        return false;
    }
}