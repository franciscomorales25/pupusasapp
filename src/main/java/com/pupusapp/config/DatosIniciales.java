package com.pupusapp.config;

import com.pupusapp.model.Pupusa;
import com.pupusapp.repository.PupusaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Carga el menú inicial en la base de datos H2 al arrancar la app.
 */
@Configuration
public class DatosIniciales {

    @Bean
    CommandLineRunner cargarMenu(PupusaRepository repo) {
        return args -> {
            repo.save(new Pupusa(null, "Revuelta", 0.75, "PUP-REV-001"));
            repo.save(new Pupusa(null, "Queso con loroco", 0.85, "PUP-LOR-002"));
            repo.save(new Pupusa(null, "Frijol con queso", 0.70, "PUP-FRI-003"));
            repo.save(new Pupusa(null, "Chicharrón", 0.80, "PUP-CHI-004"));
        };
    }
}
