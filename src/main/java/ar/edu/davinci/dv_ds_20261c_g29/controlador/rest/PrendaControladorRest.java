package ar.edu.davinci.dv_ds_20261c_g29.controlador.rest;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Prenda;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.PrendaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prendas")
@RequiredArgsConstructor
public class PrendaControladorRest {

    private final PrendaServicio prendaServicio;

    // Trae todas las prendas
    @GetMapping
    public ResponseEntity<List<Prenda>> obtenerTodas() {
        return ResponseEntity.ok(prendaServicio.obtenerTodas());
    }

    // Busca una prenda por id
    @GetMapping("/{id}")
    public ResponseEntity<Prenda> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prendaServicio.obtenerPorId(id));
    }

    // Crea una prenda nueva
    @PostMapping
    public ResponseEntity<Prenda> guardar(@RequestBody Prenda prenda) {
        return ResponseEntity.ok(prendaServicio.guardar(prenda));
    }

    // Actualiza una prenda existente
    @PutMapping("/{id}")
    public ResponseEntity<Prenda> actualizar(@PathVariable Long id, @RequestBody Prenda prenda) {
        return ResponseEntity.ok(prendaServicio.actualizar(id, prenda));
    }

    // Elimina una prenda
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        prendaServicio.eliminar(id);
        return ResponseEntity.ok().build();
    }
}