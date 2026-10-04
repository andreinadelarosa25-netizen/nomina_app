package com.nomina.servicio;

import com.nomina.modelo.Empleado;

/**
 * Contrato para cualquier deducción obligatoria.
 * Para agregar una deducción nueva se crea otra clase que lo implemente,
 * sin modificar las que ya existen.
 */
public interface Deduccion {
    double calcular(Empleado empleado);
}