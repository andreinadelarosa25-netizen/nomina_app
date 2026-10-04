package com.nomina.servicio;

import com.nomina.modelo.EmpleadoAsalariado;
import com.nomina.modelo.EmpleadoPorComision;
import com.nomina.modelo.EmpleadoPorHoras;
import com.nomina.modelo.EmpleadoTemporal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraNominaTest {

    private static final double MARGEN = 0.01;

    private CalculadoraNomina calculadora;

    @BeforeEach
    void preparar() {
        calculadora = new CalculadoraNomina(
                List.of(new DeduccionPension(), new DeduccionArl(), new DeduccionFondoAhorro()),
                List.of(new BeneficioAlimentacion()));
    }

    @Test
    void asalariadoConMasDeCincoAniosRecibeBonoYAlimentacion() {
        ReciboNomina recibo = calculadora.calcular(new EmpleadoAsalariado("Ana", 4_000_000, 6));

        assertEquals(4_000_000, recibo.salarioBruto(), MARGEN);
        assertEquals(400_000, recibo.bonos(), MARGEN);
        assertEquals(1_000_000, recibo.beneficios(), MARGEN);
        assertEquals(180_880, recibo.deducciones(), MARGEN);
        assertEquals(5_219_120, recibo.salarioNeto(), MARGEN);
    }

    @Test
    void asalariadoConExactamenteCincoAniosNoRecibeBono() {
        EmpleadoAsalariado empleado = new EmpleadoAsalariado("Ana", 4_000_000, 5);

        assertEquals(0, empleado.calcularBonos(), MARGEN);
    }

    @Test
    void empleadoPorHorasPagaHorasExtrasAUnoPuntoCinco() {
        ReciboNomina recibo = calculadora.calcular(new EmpleadoPorHoras("Luis", 20_000, 45, 18, true));

        assertEquals(950_000, recibo.salarioBruto(), MARGEN);
        assertEquals(0, recibo.bonos(), MARGEN);
        assertEquals(0, recibo.beneficios(), MARGEN);
        assertEquals(61_959, recibo.deducciones(), MARGEN);
        assertEquals(888_041, recibo.salarioNeto(), MARGEN);
    }

    @Test
    void empleadoPorHorasConMenosDeUnAnioNoAccedeAlFondo() {
        EmpleadoPorHoras empleado = new EmpleadoPorHoras("Luis", 20_000, 40, 6, true);

        assertEquals(false, empleado.accedeAlFondoAhorro());
    }

    @Test
    void empleadoPorComisionConVentasAltasRecibeBonoDelTresPorCiento() {
        ReciboNomina recibo = calculadora.calcular(
                new EmpleadoPorComision("Maria", 2_000_000, 0.05, 25_000_000));

        assertEquals(3_250_000, recibo.salarioBruto(), MARGEN);
        assertEquals(750_000, recibo.bonos(), MARGEN);
        assertEquals(1_000_000, recibo.beneficios(), MARGEN);
        assertEquals(4_853_035, recibo.salarioNeto(), MARGEN);
    }

    @Test
    void empleadoPorComisionConVentasExactasDeVeinteMillonesNoRecibeBono() {
        EmpleadoPorComision empleado = new EmpleadoPorComision("Maria", 2_000_000, 0.05, 20_000_000);

        assertEquals(0, empleado.calcularBonos(), MARGEN);
    }

    @Test
    void empleadoTemporalNoRecibeBonosNiBeneficios() {
        ReciboNomina recibo = calculadora.calcular(new EmpleadoTemporal("Carlos", 2_500_000, 6));

        assertEquals(0, recibo.bonos(), MARGEN);
        assertEquals(0, recibo.beneficios(), MARGEN);
        assertEquals(2_386_950, recibo.salarioNeto(), MARGEN);
    }

    @Test
    void salarioNetoNegativoLanzaExcepcion() {
        CalculadoraNomina conDeduccionExcesiva =
                new CalculadoraNomina(List.of(empleado -> 10_000_000), List.of());
        EmpleadoTemporal empleado = new EmpleadoTemporal("Carlos", 2_500_000, 6);

        assertThrows(IllegalStateException.class, () -> conDeduccionExcesiva.calcular(empleado));
    }
}