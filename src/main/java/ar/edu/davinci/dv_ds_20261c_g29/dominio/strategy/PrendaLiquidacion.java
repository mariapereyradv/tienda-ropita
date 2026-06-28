package ar.edu.davinci.dv_ds_20261c_g29.dominio.strategy;

public class PrendaLiquidacion implements EstadoPrendaStrategy {

    // Siempre es el 50% del precio base
    private static final Double PORCENTAJE = 0.50;

    @Override
    public Double calcularPrecio(Double precioBase) {
        return precioBase * PORCENTAJE;
    }
}