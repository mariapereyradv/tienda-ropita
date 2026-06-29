package ar.edu.davinci.dv_ds_20261c_g29.repositorio;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}