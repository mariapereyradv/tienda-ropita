package ar.edu.davinci.dv_ds_20261c_g29.controlador.web;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Prenda;
import ar.edu.davinci.dv_ds_20261c_g29.dominio.enums.EstadoPrenda;
import ar.edu.davinci.dv_ds_20261c_g29.dominio.enums.TipoPrenda;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.PrendaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prendas")
@RequiredArgsConstructor
public class PrendaControladorWeb {

    private final PrendaServicio prendaServicio;

    // Lista todas las prendas
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("prendas", prendaServicio.obtenerTodas());
        return "prendas/lista";
    }

    // Muestra el formulario para crear una prenda nueva
    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("prenda", new Prenda());
        model.addAttribute("tipos", TipoPrenda.values());
        model.addAttribute("estados", EstadoPrenda.values());
        return "prendas/formulario";
    }

    // Guarda la prenda nueva
    @PostMapping("/nueva")
    public String guardar(@ModelAttribute Prenda prenda) {
        prendaServicio.guardar(prenda);
        return "redirect:/prendas";
    }

    // Muestra el formulario para editar una prenda
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("prenda", prendaServicio.obtenerPorId(id));
        model.addAttribute("tipos", TipoPrenda.values());
        model.addAttribute("estados", EstadoPrenda.values());
        return "prendas/formulario";
    }

    // Guarda los cambios de la prenda editada
    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Prenda prenda) {
        prendaServicio.actualizar(id, prenda);
        return "redirect:/prendas";
    }

    // Elimina una prenda
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        prendaServicio.eliminar(id);
        return "redirect:/prendas";
    }
}