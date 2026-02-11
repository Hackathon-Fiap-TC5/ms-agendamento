package com.fiap.agendamento.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventoComparecimentoMessageDomainTest {

    @Test
    void shouldCreateEventoComparecimentoWithNoArgsConstructor() {
        EventoComparecimentoMessageDomain domain = new EventoComparecimentoMessageDomain();
        assertNotNull(domain);
    }

    @Test
    void shouldCreateEventoComparecimentoWithAllArgsConstructor() {
        EventoComparecimentoMessageDomain domain = new EventoComparecimentoMessageDomain(
                1L, "123456789012345", "Manter medicação", 25, "Paciente estável"
        );

        assertEquals(1L, domain.getIdAgendamento());
        assertEquals("123456789012345", domain.getCns());
        assertEquals("Manter medicação", domain.getSugestaoConduta());
        assertEquals(25, domain.getIccScore());
        assertEquals("Paciente estável", domain.getJustificativa());
    }

    @Test
    void shouldSetAndGetFields() {
        EventoComparecimentoMessageDomain domain = new EventoComparecimentoMessageDomain();
        domain.setIdAgendamento(10L);
        domain.setCns("987654321098765");
        domain.setSugestaoConduta("Retornar em 15 dias");
        domain.setIccScore(30);
        domain.setJustificativa("Necessita acompanhamento");

        assertEquals(10L, domain.getIdAgendamento());
        assertEquals("987654321098765", domain.getCns());
        assertEquals("Retornar em 15 dias", domain.getSugestaoConduta());
        assertEquals(30, domain.getIccScore());
        assertEquals("Necessita acompanhamento", domain.getJustificativa());
    }
}
