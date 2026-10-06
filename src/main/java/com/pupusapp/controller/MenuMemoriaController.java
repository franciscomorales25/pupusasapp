package com.pupusapp.controller;

import com.pupusapp.model.Pupusa;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Primer servicio REST (sección 6.3.5): datos simulados en una lista en memoria,
 * sin servicio ni base de datos. Se pierden al reiniciar la app.
 * Pruébalo: http://localhost:8080/api/memoria/pupusas
 */
@RestController
@RequestMapping("/api/memoria/pupusas")
@Tag(name = "Menú en memoria", description = "Primer servicio REST, sin base de datos")
public class MenuMemoriaController {

    private final List<Pupusa> pupusas = new ArrayList<>();

    public MenuMemoriaController() {
        pupusas.add(new Pupusa(1L, "Revuelta", 0.75, "MEM-REV"));
        pupusas.add(new Pupusa(2L, "Queso con loroco", 0.85, "MEM-LOR"));
        pupusas.add(new Pupusa(3L, "Frijol con queso", 0.70, "MEM-FRI"));
    }

    // Endpoint 1: obtener todas
    @GetMapping
    public List<Pupusa> obtenerTodas() {
        return pupusas;
    }

    // Reto: buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pupusa> obtenerPorId(@PathVariable Long id) {
        return pupusas.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST para probar con Postman
    @PostMapping
    public ResponseEntity<Pupusa> crear(@RequestBody Pupusa nueva) {
        pupusas.add(nueva);
        return ResponseEntity.status(201).body(nueva);
    }
}
