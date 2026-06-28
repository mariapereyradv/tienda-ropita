package ar.edu.davinci.dv_ds_20261c_g29.dominio.strategy;

public interface EstadoPrendaStrategy {

    // Cada estado de prenda sabe calcular su propio precio
    Double calcularPrecio(Double precioBase);
}