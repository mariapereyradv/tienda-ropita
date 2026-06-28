package ar.edu.davinci.dv_ds_20261c_g29.dominio.strategy;

public class PrendaNueva implements EstadoPrendaStrategy {

    // Precio normal, sin modificaciones
    @Override
    public Double calcularPrecio(Double precioBase) {
        return precioBase;
    }
}