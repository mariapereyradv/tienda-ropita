package ar.edu.davinci.dv_ds_20261c_g29.servicio.impl;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Venta;
import ar.edu.davinci.dv_ds_20261c_g29.excepcion.RecursoNoEncontradoExcepcion;
import ar.edu.davinci.dv_ds_20261c_g29.repositorio.VentaRepositorio;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.VentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServicioImpl implements VentaServicio {

    private final VentaRepositorio ventaRepositorio;

    @Override
    public List<Venta> obtenerTodas() {
        return ventaRepositorio.findAll();
    }

    @Override
    public Venta obtenerPorId(Long id) {
        return ventaRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Venta no encontrada con id: " + id));
    }

    public Venta guardar(Venta venta) {
        // Si no tiene detalles inicializamos la lista
        if (venta.getDetalles() == null) {
            venta.setDetalles(new ArrayList<>());
        }
        venta.setTotal(venta.calcularTotal());
        return ventaRepositorio.save(venta);
    }

    @Override
    public Venta actualizar(Long id, Venta venta) {
        obtenerPorId(id);
        venta.setId(id);
        venta.setTotal(venta.calcularTotal());
        return ventaRepositorio.save(venta);
    }

    @Override
    public void eliminar(Long id) {
        obtenerPorId(id);
        ventaRepositorio.deleteById(id);
    }

    @Override
    public Double calcularGananciasPorDia(LocalDate fecha) {
        return ventaRepositorio.findByFecha(fecha)
                .stream()
                .mapToDouble(Venta::calcularTotal)
                .sum();
    }
}