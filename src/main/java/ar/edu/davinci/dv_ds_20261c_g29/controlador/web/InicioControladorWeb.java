package ar.edu.davinci.dv_ds_20261c_g29.controlador.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioControladorWeb {

    // Página principal de la aplicación
    @GetMapping("/")
    public String inicio() {
        return "index";
    }
}