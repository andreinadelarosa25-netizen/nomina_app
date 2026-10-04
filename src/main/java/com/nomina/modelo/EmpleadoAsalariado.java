package com.nomina.modelo;

/**
 * Empleado con salario fijo mensual.
 * Recibe un bono del 10% si lleva más de 5 años en la empresa.
 */
public class EmpleadoAsalariado extends Empleado {

    private static final int ANIOS_PARA_BONO = 5;
    private static final double PORCENTAJE_BONO = 0.10;

    private final double salarioMensual;
    private final int aniosEnEmpresa;

    public EmpleadoAsalariado(String nombre, double salarioMensual, int aniosEnEmpresa) {
        super(nombre);
        if (salarioMensual < 0) {
            throw new IllegalArgumentException("El salario no puede ser negativo");
        }
        if (aniosEnEmpresa < 0) {
            throw new IllegalArgumentException("Los años en la empresa no pueden ser negativos");
        }
        this.salarioMensual = salarioMensual;
        this.aniosEnEmpresa = aniosEnEmpresa;
    }

    @Override
    public double calcularSalarioBruto() {
        return salarioMensual;
    }

    @Override
    public double calcularBonos() {
        if (aniosEnEmpresa > ANIOS_PARA_BONO) {
            return salarioMensual * PORCENTAJE_BONO;
        }
        return 0;
    }

    @Override
    public boolean esPermanente() {
        return true;
    }
}