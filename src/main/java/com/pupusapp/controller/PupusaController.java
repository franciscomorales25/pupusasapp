package com.pupusapp.controller;

import com.pupusapp.dto.PupusaDTO;
import com.pupusapp.model.Pupusa;
import com.pupusapp.service.PupusaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador: el "portero". Recibe peticiones HTTP y delega el trabajo al servicio.
 * No tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/pupusas")
@Tag(name = "Pupusas", description = "Menú de PupusApp (con base de datos H2)")
public class PupusaController {

    private final PupusaService pupusaService;

    // Inyección de dependencias por constructor
    public PupusaController(PupusaService pupusaService) {
        this.pupusaService = pupusaService;
    }

    @Operation(summary = "Crear una pupusa nueva")
    @ApiResponse(responseCode = "201", description = "Pupusa creada")
    @ApiResponse(responseCode = "400", description = "Datos inválidos (por ejemplo precio <= 0)")
    @ApiResponse(responseCode = "409", description = "El SKU ya existe")
    @PostMapping
    public ResponseEntity<Pupusa> crearPupusa(@Valid @RequestBody PupusaDTO dto) {
        Pupusa nueva = pupusaService.crearPupusa(dto);
        return ResponseEntity.status(201).body(nueva);
    }

    @Operation(summary = "Ver todo el menú")
    @GetMapping
    public List<Pupusa> obtenerTodas() {
        return pupusaService.obtenerTodas();
    }

    @Operation(summary = "Obtener una pupusa por su ID")
    @ApiResponse(responseCode = "200", description = "Pupusa encontrada")
    @ApiResponse(responseCode = "404", description = "No existe esa pupusa")
    @GetMapping("/{id}")
    public ResponseEntity<Pupusa> obtenerPorId(
            @Parameter(description = "ID de la pupusa", example = "1") @PathVariable Long id) {
        return pupusaService.obtenerPorId(id)
                .map(ResponseEntity::ok)                     // la encontró: 200 OK
                .orElse(ResponseEntity.notFound().build());  // no existe: 404
    }

    @Operation(summary = "Pupusas más baratas que un precio (ejemplo de @RequestParam)")
    @GetMapping("/baratas")
    public List<Pupusa> obtenerBaratas(
            @Parameter(description = "Precio máximo", example = "0.80")
            @RequestParam(defaultValue = "1.0") double maxPrecio) {
        return pupusaService.obtenerMasBaratasQue(maxPrecio);
    }
}
