package com.nomina.servicio;

import com.nomina.modelo.Empleado;

/** Bono de alimentación: $1.000.000 al mes, solo para empleados permanentes. */
public class BeneficioAlimentacion implements Beneficio {

    private static final double VALOR_MENSUAL = 1_000_000;

    @Override
    public double calcular(Empleado empleado) {
        if (empleado.esPermanente()) {
            return VALOR_MENSUAL;
        }
        return 0;
    }
}