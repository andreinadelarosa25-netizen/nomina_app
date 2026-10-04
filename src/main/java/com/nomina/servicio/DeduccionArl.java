package com.nomina.servicio;

import com.nomina.modelo.Empleado;

/** ARL: 0,522% del salario bruto (supuesto: riesgo mínimo, ya que el enunciado no da el porcentaje). */
public class DeduccionArl implements Deduccion {

    private static final double PORCENTAJE = 0.00522;

    @Override
    public double calcular(Empleado empleado) {
        return empleado.calcularSalarioBruto() * PORCENTAJE;
    }
}