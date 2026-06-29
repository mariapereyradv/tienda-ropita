package ar.edu.davinci.dv_ds_20261c_g29.servicio.impl;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Venta;
import ar.edu.davinci.dv_ds_20261c_g29.excepcion.RecursoNoEncontradoExcepcion;
import ar.edu.davinci.dv_ds_20261c_g29.repositorio.VentaRepositorio;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.VentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public Venta guardar(Venta venta) {
        // Calculamos el total antes de guardar
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
    public Double calcularGananciasPorDia(Date fecha) {
        return ventaRepositorio.findByFecha(fecha)
                .stream()
                .mapToDouble(Venta::calcularTotal)
                .sum();
    }
}