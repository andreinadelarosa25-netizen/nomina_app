package com.nomina.servicio;

import com.nomina.modelo.Empleado;

/**
 * Contrato para cualquier beneficio adicional que paga la empresa.
 * Para agregar un beneficio nuevo se crea otra clase que lo implemente.
 */
public interface Beneficio {
    double calcular(Empleado empleado);
}