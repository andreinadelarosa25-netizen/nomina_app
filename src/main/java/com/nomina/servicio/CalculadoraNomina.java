package com.nomina.servicio;

import com.nomina.modelo.Empleado;

import java.util.List;

/**
 * Calcula la nómina de un empleado sumando salario, bonos y beneficios,
 * y restando las deducciones.
 * Recibe las listas de deducciones y beneficios desde afuera, así que
 * agregar una nueva no obliga a modificar esta clase.
 */
public class CalculadoraNomina {

    private final List<Deduccion> deducciones;
    private final List<Beneficio> beneficios;

    public CalculadoraNomina(List<Deduccion> deducciones, List<Beneficio> beneficios) {
        this.deducciones = deducciones;
        this.beneficios = beneficios;
    }

    public ReciboNomina calcular(Empleado empleado) {
        double salarioBruto = empleado.calcularSalarioBruto();
        double bonos = empleado.calcularBonos();
        double totalBeneficios = sumarBeneficios(empleado);
        double totalDeducciones = sumarDeducciones(empleado);

        double salarioNeto = salarioBruto + bonos + totalBeneficios - totalDeducciones;
        if (salarioNeto < 0) {
            throw new IllegalStateException("El salario neto no puede ser negativo");
        }

        return new ReciboNomina(empleado.getNombre(), salarioBruto, bonos,
                totalBeneficios, totalDeducciones, salarioNeto);
    }

    private double sumarDeducciones(Empleado empleado) {
        return deducciones.stream()
                .mapToDouble(deduccion -> deduccion.calcular(empleado))
                .sum();
    }

    private double sumarBeneficios(Empleado empleado) {
        return beneficios.stream()
                .mapToDouble(beneficio -> beneficio.calcular(empleado))
                .sum();
    }
}