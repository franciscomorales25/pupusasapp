package com.pupusapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad: representa una pupusa del menú y se guarda en la tabla "pupusa".
 * Lombok genera getters, setters, toString, equals, hashCode y constructores.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pupusa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private double precio;

    @Column(unique = true)
    private String sku;
}
