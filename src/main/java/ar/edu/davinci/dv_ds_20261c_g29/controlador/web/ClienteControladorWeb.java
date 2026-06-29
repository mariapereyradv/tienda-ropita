package ar.edu.davinci.dv_ds_20261c_g29.controlador.web;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Cliente;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteControladorWeb {

    private final ClienteServicio clienteServicio;

    // Lista todos los clientes
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteServicio.obtenerTodos());
        return "clientes/lista";
    }

    // Muestra el formulario para crear un cliente nuevo
    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario";
    }

    // Guarda el cliente nuevo
    @PostMapping("/nuevo")
    public String guardar(@ModelAttribute Cliente cliente) {
        clienteServicio.guardar(cliente);
        return "redirect:/clientes";
    }

    // Muestra el formulario para editar un cliente
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", clienteServicio.obtenerPorId(id));
        return "clientes/formulario";
    }

    // Guarda los cambios del cliente editado
    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        clienteServicio.actualizar(id, cliente);
        return "redirect:/clientes";
    }

    // Elimina un cliente
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        clienteServicio.eliminar(id);
        return "redirect:/clientes";
    }
}
