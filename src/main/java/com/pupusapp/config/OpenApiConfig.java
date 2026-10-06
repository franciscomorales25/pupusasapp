package com.pupusapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Información general que aparece arriba en Swagger UI.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pupusAppOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("PupusApp API")
                .version("1.0.0")
                .description("API de ejemplo para la Unidad 6: pide tus pupusas a domicilio.")
                .contact(new Contact().name("Tutoría POO").email("tutoria@ejemplo.com")));
    }
}
