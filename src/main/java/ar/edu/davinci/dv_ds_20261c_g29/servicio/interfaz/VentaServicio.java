package ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Venta;

import java.util.Date;
import java.util.List;

public interface VentaServicio {

    List<Venta> obtenerTodas();

    Venta obtenerPorId(Long id);

    Venta guardar(Venta venta);

    Venta actualizar(Long id, Venta venta);

    void eliminar(Long id);

    // Calcula las ganancias de un dia especifico
    Double calcularGananciasPorDia(Date fecha);
}