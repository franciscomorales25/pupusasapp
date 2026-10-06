package com.pupusapp.service;

import com.pupusapp.dto.PupusaDTO;
import com.pupusapp.model.Pupusa;
import com.pupusapp.repository.PupusaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio: el "cerebro". Aquí viven las reglas de negocio de PupusApp.
 * Usa inyección por constructor (no hace falta @Autowired con un solo constructor).
 */
@Service
public class PupusaService {

    private final PupusaRepository pupusaRepository;

    public PupusaService(PupusaRepository pupusaRepository) {
        this.pupusaRepository = pupusaRepository;
    }

    @Transactional
    public Pupusa crearPupusa(PupusaDTO dto) {
        // Regla 1: no hay pupusas gratis ni con precio negativo
        if (dto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero.");
        }
        // Regla 2: no se puede repetir el SKU
        if (pupusaRepository.findBySku(dto.getSku()).isPresent()) {
            throw new IllegalStateException("El SKU ya existe.");
        }

        Pupusa pupusa = new Pupusa();
        pupusa.setNombre(dto.getNombre());
        pupusa.setPrecio(dto.getPrecio());
        pupusa.setSku(dto.getSku());

        return pupusaRepository.save(pupusa);
    }

    @Transactional(readOnly = true)
    public List<Pupusa> obtenerTodas() {
        return pupusaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Pupusa> obtenerPorId(Long id) {
        return pupusaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Pupusa> obtenerMasBaratasQue(double precioMaximo) {
        return pupusaRepository.findByPrecioLessThan(precioMaximo);
    }
}
