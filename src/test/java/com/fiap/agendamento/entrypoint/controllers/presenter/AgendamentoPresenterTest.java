package com.fiap.agendamento.entrypoint.controllers.presenter;

import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamento.infrastructure.publisher.payload.AgendamentoMessageEvent;
import com.fiap.agendamentoDomain.gen.model.AgendamentoResponseDto;
import com.fiap.agendamentoDomain.gen.model.CriarAgendamentoRequestDto;
import com.fiap.agendamentoDomain.gen.model.EventoAgendamentoMessagePayloadDto;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoPresenterTest {

    @Test
    void shouldConvertToAgendamentoDomain() {
        CriarAgendamentoRequestDto requestDto = new CriarAgendamentoRequestDto();
        requestDto.setCns("123456789012345");
        requestDto.setDataConsulta(OffsetDateTime.now());
        requestDto.setEspecialidade("CARDIOLOGIA");
        requestDto.setCrmMedico("CRM-SP-123456");
        requestDto.setCnesLocal("1234567");

        AgendamentoDomain result = AgendamentoPresenter.toAgendamentoDomain(requestDto);

        assertNotNull(result);
        assertEquals("123456789012345", result.getCns());
        assertEquals("CARDIOLOGIA", result.getEspecialidade());
        assertEquals("CRM-SP-123456", result.getCrmMedico());
        assertEquals("1234567", result.getCnesLocal());
    }

    @Test
    void shouldConvertToAgendamentoResponseDto() {
        StatusConsultaDomain statusConsulta = new StatusConsultaDomain(1L, "AGENDADO");
        StatusNotificacaoDomain statusNotificacao = new StatusNotificacaoDomain(1L, "NAO_ENVIADA");
        AgendamentoDomain domain = new AgendamentoDomain(
                1L, "123456789012345", OffsetDateTime.now(),
                "CARDIOLOGIA", "CRM-SP-123456", "1234567",
                statusConsulta, statusNotificacao
        );

        AgendamentoResponseDto result = AgendamentoPresenter.toAgendamentoResponseDto(domain);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("123456789012345", result.getCns());
        assertEquals("CARDIOLOGIA", result.getEspecialidade());
    }

    @Test
    void shouldConvertToListAgendamentoResponseDto() {
        StatusConsultaDomain statusConsulta = new StatusConsultaDomain(1L, "AGENDADO");
        StatusNotificacaoDomain statusNotificacao = new StatusNotificacaoDomain(1L, "NAO_ENVIADA");
        AgendamentoDomain domain = new AgendamentoDomain(
                1L, "123456789012345", OffsetDateTime.now(),
                "CARDIOLOGIA", "CRM-SP-123456", "1234567",
                statusConsulta, statusNotificacao
        );
        List<AgendamentoDomain> domains = Arrays.asList(domain);

        List<AgendamentoResponseDto> result = AgendamentoPresenter.toListAgendamentoResponseDto(domains);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void shouldConvertEmptyListToAgendamentoResponseDto() {
        List<AgendamentoResponseDto> result = AgendamentoPresenter.toListAgendamentoResponseDto(Collections.emptyList());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldBuildStatusNotificacao() {
        StatusNotificacaoDomain result = AgendamentoPresenter.toBuildStatusNotificacao(5L);

        assertNotNull(result);
        assertEquals(5L, result.getId());
    }

    @Test
    void shouldBuildAgendamentoDomainStatusConsulta() {
        AgendamentoDomain result = AgendamentoPresenter.toBuildAgendamentoDomainStatusConsulta(3L);

        assertNotNull(result);
        assertNotNull(result.getStatusConsultaDomain());
        assertEquals(3L, result.getStatusConsultaDomain().getId());
    }

    @Test
    void shouldBuildPayloadPublisher() {
        StatusConsultaDomain statusConsulta = new StatusConsultaDomain(1L, "COMPARECEU");
        StatusNotificacaoDomain statusNotificacao = new StatusNotificacaoDomain(2L, "CONFIRMOU");
        OffsetDateTime dataEvento = OffsetDateTime.now();
        AgendamentoMessageEvent event = new AgendamentoMessageEvent(
                10L, "123456789012345", statusConsulta, statusNotificacao, dataEvento
        );

        EventoAgendamentoMessagePayloadDto result = AgendamentoPresenter.toBuildPayloadPublisher(event);

        assertNotNull(result);
        assertEquals(10L, result.getIdAgendamento());
        assertEquals("123456789012345", result.getCns());
        assertEquals("COMPARECEU", result.getStatusConsulta());
        assertEquals("CONFIRMOU", result.getStatusNotificacao());
        assertEquals(dataEvento, result.getDataEvento());
    }
}
