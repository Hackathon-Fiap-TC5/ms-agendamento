package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.exception.AgendamentoNaoEncontradoException;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultarAgendamentoPorIdUseCaseImplTest {

    @Mock
    private AgendamentoDomainService agendamentoDomainService;

    @InjectMocks
    private ConsultarAgendamentoPorIdUseCaseImpl useCase;

    private AgendamentoDomain agendamentoDomain;

    @BeforeEach
    void setUp() {
        StatusConsultaDomain statusConsulta = new StatusConsultaDomain(1L, "AGENDADO");
        StatusNotificacaoDomain statusNotificacao = new StatusNotificacaoDomain(1L, "NAO_ENVIADA");

        agendamentoDomain = new AgendamentoDomain(
                1L,
                "123456789012345",
                OffsetDateTime.now(),
                "CARDIOLOGIA",
                "CRM-SP-123456",
                "1234567",
                statusConsulta,
                statusNotificacao
        );
    }

    @Test
    void shouldConsultarAgendamentoPorId() {
        when(agendamentoDomainService.buscarAgendamentoDomainPorId(1L)).thenReturn(agendamentoDomain);

        AgendamentoDomain result = useCase.consultarAgendamentoPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("123456789012345", result.getCns());
        verify(agendamentoDomainService, times(1)).buscarAgendamentoDomainPorId(1L);
    }

    @Test
    void shouldThrowAgendamentoNaoEncontradoExceptionWhenAgendamentoNotFound() {
        when(agendamentoDomainService.buscarAgendamentoDomainPorId(999L))
                .thenThrow(new AgendamentoNaoEncontradoException());

        assertThrows(AgendamentoNaoEncontradoException.class, () ->
                useCase.consultarAgendamentoPorId(999L));

        verify(agendamentoDomainService, times(1)).buscarAgendamentoDomainPorId(999L);
    }
}
