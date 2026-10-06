package com.pupusapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO: la "papeleta" con solo los datos que el cliente puede mandar.
 * No incluye el id porque ese lo asigna la base de datos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para registrar una pupusa en el menú")
public class PupusaDTO {

    @NotBlank
    @Schema(description = "Nombre de la pupusa", example = "Revuelta")
    private String nombre;

    @Schema(description = "Precio en USD (debe ser mayor a cero)", example = "0.75")
    private double precio;

    @NotBlank
    @Schema(description = "Código único del producto", example = "PUP-REV-001")
    private String sku;
}
