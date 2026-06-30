package ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ventas")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fecha en que se realizó la venta
    private LocalDate fecha;

    // Cliente que realizó la compra
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Lista de prendas vendidas con su cantidad
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "venta_id")
    private List<DetalleVenta> detalles = new ArrayList<>();

    // Total de la venta
    private Double total;

    // Cada tipo de venta calcula su total diferente
    public abstract Double calcularTotal();
}