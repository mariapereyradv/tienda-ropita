package ar.edu.davinci.dv_ds_20261c_g29.repositorio;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrendaRepositorio extends JpaRepository<Prenda, Long> {
    // Spring genera los metodos basicos:
    // findAll(), findById(), save(), deleteById()
}