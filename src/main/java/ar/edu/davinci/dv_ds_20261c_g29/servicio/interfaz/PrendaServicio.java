package ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Prenda;

import java.util.List;

public interface PrendaServicio {

    // Trae todas las prendas
    List<Prenda> obtenerTodas();

    // Busca una prenda por su id
    Prenda obtenerPorId(Long id);

    // Guarda una prenda nueva
    Prenda guardar(Prenda prenda);

    // Actualiza una prenda existente
    Prenda actualizar(Long id, Prenda prenda);

    // Elimina una prenda
    void eliminar(Long id);
}