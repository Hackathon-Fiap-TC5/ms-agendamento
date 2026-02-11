package com.fiap.agendamento.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusConsultaDomainTest {

    @Test
    void shouldCreateStatusConsultaWithNoArgsConstructor() {
        StatusConsultaDomain domain = new StatusConsultaDomain();
        assertNotNull(domain);
    }

    @Test
    void shouldCreateStatusConsultaWithAllArgsConstructor() {
        StatusConsultaDomain domain = new StatusConsultaDomain(1L, "COMPARECEU");

        assertEquals(1L, domain.getId());
        assertEquals("COMPARECEU", domain.getStatus());
    }

    @Test
    void shouldSetAndGetFields() {
        StatusConsultaDomain domain = new StatusConsultaDomain();
        domain.setId(5L);
        domain.setStatus("FALTOU");

        assertEquals(5L, domain.getId());
        assertEquals("FALTOU", domain.getStatus());
    }
}
