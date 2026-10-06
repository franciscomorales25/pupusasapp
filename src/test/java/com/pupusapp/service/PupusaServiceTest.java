package com.pupusapp.service;

import com.pupusapp.dto.PupusaDTO;
import com.pupusapp.repository.PupusaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Arquitectura limpia: la regla de negocio se prueba SIN base de datos ni servidor.
 * Usamos un repositorio falso (mock) de Mockito.
 */
class PupusaServiceTest {

    @Test
    void noPermitePupusasConPrecioCero() {
        PupusaRepository repoFalso = Mockito.mock(PupusaRepository.class);
        PupusaService service = new PupusaService(repoFalso);

        PupusaDTO gratis = new PupusaDTO("Revuelta", 0, "PUP-TEST");

        assertThrows(IllegalArgumentException.class, () -> service.crearPupusa(gratis));
    }
}
