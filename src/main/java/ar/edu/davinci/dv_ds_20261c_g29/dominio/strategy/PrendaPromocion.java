package ar.edu.davinci.dv_ds_20261c_g29.dominio.strategy;

public class PrendaPromocion implements EstadoPrendaStrategy {

    // Descuento fijo decidido por el usuario
    private Double descuento;

    public PrendaPromocion(Double descuento) {
        this.descuento = descuento;
    }

    @Override
    public Double calcularPrecio(Double precioBase) {
        return precioBase - descuento;
    }
}