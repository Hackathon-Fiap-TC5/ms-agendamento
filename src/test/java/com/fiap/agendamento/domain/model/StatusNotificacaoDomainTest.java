package com.fiap.agendamento.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusNotificacaoDomainTest {

    @Test
    void shouldCreateStatusNotificacaoWithNoArgsConstructor() {
        StatusNotificacaoDomain domain = new StatusNotificacaoDomain();
        assertNotNull(domain);
    }

    @Test
    void shouldCreateStatusNotificacaoWithAllArgsConstructor() {
        StatusNotificacaoDomain domain = new StatusNotificacaoDomain(1L, "CONFIRMOU_48H_ANTECEDENCIA");

        assertEquals(1L, domain.getId());
        assertEquals("CONFIRMOU_48H_ANTECEDENCIA", domain.getStatus());
    }

    @Test
    void shouldSetAndGetFields() {
        StatusNotificacaoDomain domain = new StatusNotificacaoDomain();
        domain.setId(5L);
        domain.setStatus("CONFIRMOU_24H_ANTECEDENCIA");

        assertEquals(5L, domain.getId());
        assertEquals("CONFIRMOU_24H_ANTECEDENCIA", domain.getStatus());
    }
}
