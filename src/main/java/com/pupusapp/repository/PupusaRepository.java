package com.pupusapp.repository;

import com.pupusapp.model.Pupusa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio: la "bóveda". Solo escribimos la interfaz y Spring Data JPA la implementa.
 * Ya trae gratis: save, findAll, findById, deleteById, count...
 */
@Repository
public interface PupusaRepository extends JpaRepository<Pupusa, Long> {

    // Spring arma solo: SELECT * FROM pupusa WHERE sku = ?
    Optional<Pupusa> findBySku(String sku);

    // Spring arma solo: SELECT * FROM pupusa WHERE precio < ?
    List<Pupusa> findByPrecioLessThan(double precio);
}
