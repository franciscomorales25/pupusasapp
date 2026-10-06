package com.pupusapp.service;

import org.springframework.stereotype.Service;

/**
 * Ejemplo de @Service + inyección de dependencias (sección 6.3.4).
 */
@Service
public class SaludoService {

    public String generarSaludo(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "¡Hola, mundo!";
        }
        return "¡Hola, " + nombre + "! ¿Revueltas o de queso?";
    }
}
