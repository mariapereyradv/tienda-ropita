package ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.enums.EstadoPrenda;
import ar.edu.davinci.dv_ds_20261c_g29.dominio.enums.TipoPrenda;
import ar.edu.davinci.dv_ds_20261c_g29.dominio.strategy.EstadoPrendaStrategy;
import ar.edu.davinci.dv_ds_20261c_g29.dominio.strategy.PrendaLiquidacion;
import ar.edu.davinci.dv_ds_20261c_g29.dominio.strategy.PrendaNueva;
import ar.edu.davinci.dv_ds_20261c_g29.dominio.strategy.PrendaPromocion;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prendas")
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Double precioBase;

    @Enumerated(EnumType.STRING)
    private TipoPrenda tipo;

    @Enumerated(EnumType.STRING)
    private EstadoPrenda estado;

    private Integer stock;

    // El descuento solo aplica si el estado es PROMOCION
    private Double descuento;

    @Transient
    private EstadoPrendaStrategy estrategia;

    // Calcula el precio segun el estado de la prenda
    public Double calcularPrecio() {
        switch (estado) {
            case NUEVA:
                estrategia = new PrendaNueva();
                break;
            case PROMOCION:
                estrategia = new PrendaPromocion(descuento);
                break;
            case LIQUIDACION:
                estrategia = new PrendaLiquidacion();
                break;
        }
        return estrategia.calcularPrecio(precioBase);
    }

    // Agrega unidades al stock
    public void agregarStock(Integer cantidad) {
        this.stock += cantidad;
    }

    // Descuenta unidades del stock al vender
    public void descontarStock(Integer cantidad) {
        this.stock -= cantidad;
    }
}