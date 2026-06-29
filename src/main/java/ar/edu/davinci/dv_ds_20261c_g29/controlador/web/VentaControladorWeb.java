package ar.edu.davinci.dv_ds_20261c_g29.controlador.web;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.VentaEfectivo;
import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.VentaTarjeta;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.ClienteServicio;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.PrendaServicio;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.VentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String guardarEfectivo(@ModelAttribute VentaEfectivo venta) {
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

    // Guarda la venta con tarjeta
    @PostMapping("/nueva/tarjeta")
    public String guardarTarjeta(@ModelAttribute VentaTarjeta venta) {
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