package com.nomina.modelo;

/**
 * Empleado que cobra por horas trabajadas.
 * Las horas que pasan de 40 se pagan a 1.5 veces la tarifa normal.
 * No recibe bonos.
 */
public class EmpleadoPorHoras extends Empleado {

    private static final double HORAS_NORMALES = 40;
    private static final double FACTOR_HORA_EXTRA = 1.5;
    private static final int MESES_PARA_FONDO = 12;

    private final double tarifaPorHora;
    private final double horasTrabajadas;
    private final int mesesEnEmpresa;
    private final boolean aceptaFondoAhorro;

    public EmpleadoPorHoras(String nombre, double tarifaPorHora, double horasTrabajadas,
                            int mesesEnEmpresa, boolean aceptaFondoAhorro) {
        super(nombre);
        if (tarifaPorHora < 0) {
            throw new IllegalArgumentException("La tarifa no puede ser negativa");
        }
        if (horasTrabajadas < 0) {
            throw new IllegalArgumentException("Las horas trabajadas no pueden ser negativas");
        }
        if (mesesEnEmpresa < 0) {
            throw new IllegalArgumentException("Los meses en la empresa no pueden ser negativos");
        }
        this.tarifaPorHora = tarifaPorHora;
        this.horasTrabajadas = horasTrabajadas;
        this.mesesEnEmpresa = mesesEnEmpresa;
        this.aceptaFondoAhorro = aceptaFondoAhorro;
    }

    @Override
    public double calcularSalarioBruto() {
        if (horasTrabajadas <= HORAS_NORMALES) {
            return horasTrabajadas * tarifaPorHora;
        }
        double horasExtras = horasTrabajadas - HORAS_NORMALES;
        double pagoNormal = HORAS_NORMALES * tarifaPorHora;
        double pagoExtras = horasExtras * tarifaPorHora * FACTOR_HORA_EXTRA;
        return pagoNormal + pagoExtras;
    }

    /** Tiene derecho al fondo de ahorro si lleva más de 1 año y lo acepta. */
    @Override
    public boolean accedeAlFondoAhorro() {
        return mesesEnEmpresa > MESES_PARA_FONDO && aceptaFondoAhorro;
    }
}