package ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comercios")
public class Comercio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // Lista de todas las ventas del comercio
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comercio_id")
    private List<Venta> ventas;

    // Calcula las ganancias de un dia especifico
    public Double calcularGananciasPorDia(LocalDate fecha) {
        return ventas.stream()
                .filter(venta -> venta.getFecha().equals(fecha))
                .mapToDouble(Venta::calcularTotal)
                .sum();
    }
}