package ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // La prenda que se vendió
    @ManyToOne
    @JoinColumn(name = "prenda_id")
    private Prenda prenda;

    // Cuántas unidades se vendieron
    private Integer cantidad;
}