package com.nomina.servicio;

import com.nomina.modelo.Empleado;

/** Seguro social y pensión: 4% del salario bruto. */
public class DeduccionPension implements Deduccion {

    private static final double PORCENTAJE = 0.04;

    @Override
    public double calcular(Empleado empleado) {
        return empleado.calcularSalarioBruto() * PORCENTAJE;
    }
}