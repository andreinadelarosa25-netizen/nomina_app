package com.nomina.servicio;

/** Resultado del cálculo de nómina de un empleado. */
public record ReciboNomina(
        String nombre,
        double salarioBruto,
        double bonos,
        double beneficios,
        double deducciones,
        double salarioNeto) {
}