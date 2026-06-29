package ar.edu.davinci.dv_ds_20261c_g29.repositorio;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComercioRepositorio extends JpaRepository<Comercio, Long> {
}