package ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ventas_tarjeta")
public class VentaTarjeta extends Venta {

    // Cantidad de cuotas seleccionadas por el cliente
    private Integer cantidadCuotas;

    // Coeficiente fijo para calcular el recargo
    private static final Double COEFICIENTE = 0.01;

    // Calcula el recargo segun la formula del enunciado:
    // total + (cantidadCuotas * coeficiente + 0.01) * total
    @Override
    public Double calcularTotal() {
        Double totalBase = getDetalles().stream()
                .mapToDouble(detalle ->
                        detalle.getPrenda().calcularPrecio() * detalle.getCantidad())
                .sum();

        Double recargo = cantidadCuotas * COEFICIENTE + 0.01;
        return totalBase + (totalBase * recargo);
    }
}