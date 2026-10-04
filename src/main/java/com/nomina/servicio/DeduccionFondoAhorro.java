package com.nomina.servicio;

import com.nomina.modelo.Empleado;

/**
 * Fondo de ahorro: 2% del salario bruto depositado cada mes.
 * Solo aplica si el empleado tiene acceso y aceptó el fondo.
 */
public class DeduccionFondoAhorro implements Deduccion {

    private static final double PORCENTAJE = 0.02;

    @Override
    public double calcular(Empleado empleado) {
        if (empleado.accedeAlFondoAhorro()) {
            return empleado.calcularSalarioBruto() * PORCENTAJE;
        }
        return 0;
    }
}