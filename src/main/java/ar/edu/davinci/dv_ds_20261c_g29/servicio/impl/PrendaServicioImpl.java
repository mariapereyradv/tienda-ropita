package ar.edu.davinci.dv_ds_20261c_g29.servicio.impl;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Prenda;
import ar.edu.davinci.dv_ds_20261c_g29.excepcion.RecursoNoEncontradoExcepcion;
import ar.edu.davinci.dv_ds_20261c_g29.repositorio.PrendaRepositorio;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.PrendaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrendaServicioImpl implements PrendaServicio {

    private final PrendaRepositorio prendaRepositorio;

    @Override
    public List<Prenda> obtenerTodas() {
        return prendaRepositorio.findAll();
    }

    @Override
    public Prenda obtenerPorId(Long id) {
        return prendaRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Prenda no encontrada con id: " + id));
    }

    @Override
    public Prenda guardar(Prenda prenda) {
        return prendaRepositorio.save(prenda);
    }

    @Override
    public Prenda actualizar(Long id, Prenda prenda) {
        obtenerPorId(id);
        prenda.setId(id);
        return prendaRepositorio.save(prenda);
    }

    @Override
    public void eliminar(Long id) {
        obtenerPorId(id);
        prendaRepositorio.deleteById(id);
    }
}