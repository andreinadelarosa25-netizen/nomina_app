package com.nomina.modelo;

/**
 * Empleado con salario base más un porcentaje sobre sus ventas.
 * Si las ventas superan 20.000.000 recibe un bono adicional del 3% sobre las ventas.
 */
public class EmpleadoPorComision extends Empleado {

    private static final double META_VENTAS_PARA_BONO = 20_000_000;
    private static final double PORCENTAJE_BONO = 0.03;

    private final double salarioBase;
    private final double porcentajeComision;
    private final double ventas;

    public EmpleadoPorComision(String nombre, double salarioBase,
                               double porcentajeComision, double ventas) {
        super(nombre);
        if (salarioBase < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo");
        }
        if (porcentajeComision < 0) {
            throw new IllegalArgumentException("El porcentaje de comisión no puede ser negativo");
        }
        if (ventas < 0) {
            throw new IllegalArgumentException("Las ventas no pueden ser menores a $0");
        }
        this.salarioBase = salarioBase;
        this.porcentajeComision = porcentajeComision;
        this.ventas = ventas;
    }

    @Override
    public double calcularSalarioBruto() {
        return salarioBase + (ventas * porcentajeComision);
    }

    @Override
    public double calcularBonos() {
        if (ventas > META_VENTAS_PARA_BONO) {
            return ventas * PORCENTAJE_BONO;
        }
        return 0;
    }

    @Override
    public boolean esPermanente() {
        return true;
    }
}