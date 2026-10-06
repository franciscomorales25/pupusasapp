package com.pupusapp.controller;

import com.pupusapp.service.SaludoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ejemplo de @Autowired (sección 6.3.4).
 * Pruébalo: http://localhost:8080/saludo?nombre=Karla
 */
@RestController
@Tag(name = "Saludo", description = "Ejemplo de inyección de dependencias")
public class SaludoController {

    private final SaludoService saludoService;

    @Autowired // opcional con un solo constructor, se deja para que se vea en clase
    public SaludoController(SaludoService saludoService) {
        this.saludoService = saludoService;
    }

    @GetMapping("/saludo")
    public String saludar(@RequestParam(value = "nombre", required = false) String nombre) {
        return saludoService.generarSaludo(nombre);
    }
}
