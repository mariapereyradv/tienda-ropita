package ar.edu.davinci.dv_ds_20261c_g29.controlador.rest;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Venta;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.VentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaControladorRest {

    private final VentaServicio ventaServicio;

    @GetMapping
    public ResponseEntity<List<Venta>> obtenerTodas() {
        return ResponseEntity.ok(ventaServicio.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaServicio.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Venta> guardar(@RequestBody Venta venta) {
        return ResponseEntity.ok(ventaServicio.guardar(venta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable Long id, @RequestBody Venta venta) {
        return ResponseEntity.ok(ventaServicio.actualizar(id, venta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ventaServicio.eliminar(id);
        return ResponseEntity.ok().build();
    }

    // Calcula las ganancias de un dia especifico
    @GetMapping("/ganancias")
    public ResponseEntity<Double> calcularGananciasPorDia(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {
        return ResponseEntity.ok(ventaServicio.calcularGananciasPorDia(fecha));
    }
}