package com.fiap.agendamento.domain.model;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoDomainTest {

    @Test
    void shouldCreateAgendamentoWithNoArgsConstructor() {
        AgendamentoDomain domain = new AgendamentoDomain();
        assertNotNull(domain);
    }

    @Test
    void shouldCreateAgendamentoWithAllArgsConstructor() {
        StatusConsultaDomain statusConsulta = new StatusConsultaDomain(1L, "AGENDADO");
        StatusNotificacaoDomain statusNotificacao = new StatusNotificacaoDomain(1L, "NAO_ENVIADA");
        OffsetDateTime dataConsulta = OffsetDateTime.now();

        AgendamentoDomain domain = new AgendamentoDomain(
                1L, "123456789012345", dataConsulta,
                "CARDIOLOGIA", "CRM-SP-123456", "1234567",
                statusConsulta, statusNotificacao
        );

        assertEquals(1L, domain.getId());
        assertEquals("123456789012345", domain.getCns());
        assertEquals(dataConsulta, domain.getDataConsulta());
        assertEquals("CARDIOLOGIA", domain.getEspecialidade());
        assertEquals("CRM-SP-123456", domain.getCrmMedico());
        assertEquals("1234567", domain.getCnesLocal());
        assertEquals(statusConsulta, domain.getStatusConsultaDomain());
        assertEquals(statusNotificacao, domain.getStatusNotificacaoDomain());
    }

    @Test
    void shouldSetAndGetAllFields() {
        AgendamentoDomain domain = new AgendamentoDomain();
        StatusConsultaDomain statusConsulta = new StatusConsultaDomain(1L, "AGENDADO");
        StatusNotificacaoDomain statusNotificacao = new StatusNotificacaoDomain(1L, "NAO_ENVIADA");

        domain.setId(5L);
        domain.setCns("987654321098765");
        domain.setDataConsulta(OffsetDateTime.now());
        domain.setEspecialidade("ORTOPEDIA");
        domain.setCrmMedico("CRM-RJ-654321");
        domain.setCnesLocal("7654321");
        domain.setStatusConsultaDomain(statusConsulta);
        domain.setStatusNotificacaoDomain(statusNotificacao);
        domain.setSugestaoConduta("Retornar em 30 dias");
        domain.setIccScore(25);
        domain.setJustificativa("Paciente estável");

        assertEquals(5L, domain.getId());
        assertEquals("987654321098765", domain.getCns());
        assertEquals("ORTOPEDIA", domain.getEspecialidade());
        assertEquals("CRM-RJ-654321", domain.getCrmMedico());
        assertEquals("7654321", domain.getCnesLocal());
        assertEquals(statusConsulta, domain.getStatusConsultaDomain());
        assertEquals(statusNotificacao, domain.getStatusNotificacaoDomain());
        assertEquals("Retornar em 30 dias", domain.getSugestaoConduta());
        assertEquals(25, domain.getIccScore());
        assertEquals("Paciente estável", domain.getJustificativa());
    }
}
