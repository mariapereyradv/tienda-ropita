package ar.edu.davinci.dv_ds_20261c_g29.repositorio;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepositorio extends JpaRepository<Venta, Long> {

    // Busca todas las ventas de una fecha especifica
    List<Venta> findByFecha(LocalDate fecha);
}