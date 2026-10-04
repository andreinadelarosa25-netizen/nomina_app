package com.nomina.servicio;

import com.nomina.modelo.EmpleadoAsalariado;
import com.nomina.modelo.EmpleadoPorComision;
import com.nomina.modelo.EmpleadoPorHoras;
import com.nomina.modelo.EmpleadoTemporal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EmpleadoValidacionesTest {

    @Test
    void nombreVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new EmpleadoTemporal("  ", 2_500_000, 6));
    }

    @Test
    void horasNegativasLanzanExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new EmpleadoPorHoras("Luis", 20_000, -1, 18, true));
    }

    @Test
    void ventasNegativasLanzanExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new EmpleadoPorComision("Maria", 2_000_000, 0.05, -1));
    }

    @Test
    void salarioNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new EmpleadoAsalariado("Ana", -1, 6));
    }
}