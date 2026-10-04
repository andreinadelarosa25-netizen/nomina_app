package com.nomina.modelo;

/**
 * Empleado con salario fijo mensual y contrato por tiempo definido.
 * No recibe bonos ni beneficios adicionales.
 */
public class EmpleadoTemporal extends Empleado {

    private final double salarioMensual;
    private final int mesesDeContrato;

    public EmpleadoTemporal(String nombre, double salarioMensual, int mesesDeContrato) {
        super(nombre);
        if (salarioMensual < 0) {
            throw new IllegalArgumentException("El salario no puede ser negativo");
        }
        if (mesesDeContrato <= 0) {
            throw new IllegalArgumentException("El contrato debe durar al menos un mes");
        }
        this.salarioMensual = salarioMensual;
        this.mesesDeContrato = mesesDeContrato;
    }

    public int getMesesDeContrato() {
        return mesesDeContrato;
    }

    @Override
    public double calcularSalarioBruto() {
        return salarioMensual;
    }
}