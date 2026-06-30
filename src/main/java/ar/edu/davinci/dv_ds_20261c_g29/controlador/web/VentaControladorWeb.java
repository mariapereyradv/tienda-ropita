package ar.edu.davinci.dv_ds_20261c_g29.controlador.web;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.DetalleVenta;
import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.VentaEfectivo;
import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.VentaTarjeta;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.ClienteServicio;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.PrendaServicio;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.VentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaControladorWeb {

    private final VentaServicio ventaServicio;
    private final ClienteServicio clienteServicio;
    private final PrendaServicio prendaServicio;

    // Lista todas las ventas
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaServicio.obtenerTodas());
        return "ventas/lista";
    }

    // Muestra el detalle de una venta
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("venta", ventaServicio.obtenerPorId(id));
        return "ventas/detalle";
    }

    // Muestra el formulario para crear una venta en efectivo
    @GetMapping("/nueva/efectivo")
    public String nuevaEfectivoForm(Model model) {
        model.addAttribute("venta", new VentaEfectivo());
        model.addAttribute("clientes", clienteServicio.obtenerTodos());
        model.addAttribute("prendas", prendaServicio.obtenerTodas());
        return "ventas/formulario-efectivo";
    }

    // Guarda la venta en efectivo
    @PostMapping("/nueva/efectivo")
    public String guardarEfectivo(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
            @RequestParam Long clienteId,
            @RequestParam(required = false) List<Long> prendaIds,
            @RequestParam(required = false) List<Integer> cantidades) {

        VentaEfectivo venta = new VentaEfectivo();
        venta.setFecha(fecha);
        venta.setCliente(clienteServicio.obtenerPorId(clienteId));

        // Armamos la lista de detalles
        List<DetalleVenta> detalles = new ArrayList<>();
        if (prendaIds != null) {
            for (int i = 0; i < prendaIds.size(); i++) {
                if (prendaIds.get(i) != null) {
                    DetalleVenta detalle = new DetalleVenta();
                    detalle.setPrenda(prendaServicio.obtenerPorId(prendaIds.get(i)));
                    detalle.setCantidad(cantidades.get(i));
                    detalles.add(detalle);
                }
            }
        }
        venta.setDetalles(detalles);
        ventaServicio.guardar(venta);
        return "redirect:/ventas";
    }

    // Muestra el formulario para crear una venta con tarjeta
    @GetMapping("/nueva/tarjeta")
    public String nuevaTarjetaForm(Model model) {
        model.addAttribute("venta", new VentaTarjeta());
        model.addAttribute("clientes", clienteServicio.obtenerTodos());
        model.addAttribute("prendas", prendaServicio.obtenerTodas());
        return "ventas/formulario-tarjeta";
    }

    @PostMapping("/nueva/tarjeta")
    public String guardarTarjeta(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
            @RequestParam Long clienteId,
            @RequestParam Integer cantidadCuotas,
            @RequestParam(required = false) List<Long> prendaIds,
            @RequestParam(required = false) List<Integer> cantidades) {

        VentaTarjeta venta = new VentaTarjeta();
        venta.setFecha(fecha);
        venta.setCliente(clienteServicio.obtenerPorId(clienteId));
        venta.setCantidadCuotas(cantidadCuotas);

        List<DetalleVenta> detalles = new ArrayList<>();
        if (prendaIds != null) {
            for (int i = 0; i < prendaIds.size(); i++) {
                if (prendaIds.get(i) != null) {
                    DetalleVenta detalle = new DetalleVenta();
                    detalle.setPrenda(prendaServicio.obtenerPorId(prendaIds.get(i)));
                    detalle.setCantidad(cantidades.get(i));
                    detalles.add(detalle);
                }
            }
        }
        venta.setDetalles(detalles);
        ventaServicio.guardar(venta);
        return "redirect:/ventas";
    }

    // Elimina una venta
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        ventaServicio.eliminar(id);
        return "redirect:/ventas";
    }
}