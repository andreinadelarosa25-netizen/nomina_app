package com.nomina;

import com.nomina.modelo.Empleado;
import com.nomina.modelo.EmpleadoAsalariado;
import com.nomina.modelo.EmpleadoPorComision;
import com.nomina.modelo.EmpleadoPorHoras;
import com.nomina.modelo.EmpleadoTemporal;
import com.nomina.servicio.BeneficioAlimentacion;
import com.nomina.servicio.CalculadoraNomina;
import com.nomina.servicio.DeduccionArl;
import com.nomina.servicio.DeduccionFondoAhorro;
import com.nomina.servicio.DeduccionPension;
import com.nomina.servicio.ReciboNomina;

import java.util.List;
import java.util.Locale;

/** Programa de demostración del sistema de nómina. */
public class Main {

    public static void main(String[] args) {
        CalculadoraNomina calculadora = new CalculadoraNomina(
                List.of(new DeduccionPension(), new DeduccionArl(), new DeduccionFondoAhorro()),
                List.of(new BeneficioAlimentacion()));

        List<Empleado> empleados = List.of(
                new EmpleadoAsalariado("Ana Pérez", 4_000_000, 6),
                new EmpleadoPorHoras("Luis Gómez", 20_000, 45, 18, true),
                new EmpleadoPorComision("María Torres", 2_000_000, 0.05, 25_000_000),
                new EmpleadoTemporal("Carlos Ruiz", 2_500_000, 6));

        for (Empleado empleado : empleados) {
            imprimirRecibo(calculadora.calcular(empleado));
        }
    }

    private static void imprimirRecibo(ReciboNomina recibo) {
        System.out.println("Empleado: " + recibo.nombre());
        System.out.println("  Salario bruto: " + formato(recibo.salarioBruto()));
        System.out.println("  Bonos:         " + formato(recibo.bonos()));
        System.out.println("  Beneficios:    " + formato(recibo.beneficios()));
        System.out.println("  Deducciones:   " + formato(recibo.deducciones()));
        System.out.println("  Salario neto:  " + formato(recibo.salarioNeto()));
        System.out.println();
    }

    private static String formato(double valor) {
        return String.format(Locale.of("es", "CO"), "$%,.2f", valor);
    }
}