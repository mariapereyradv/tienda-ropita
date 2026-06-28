package ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ventas_efectivo")
public class VentaEfectivo extends Venta {

    // El efectivo no tiene recargo, devuelve el total sin modificaciones
    @Override
    public Double calcularTotal() {
        return getDetalles().stream()
                .mapToDouble(detalle ->
                        detalle.getPrenda().calcularPrecio() * detalle.getCantidad())
                .sum();
    }
}