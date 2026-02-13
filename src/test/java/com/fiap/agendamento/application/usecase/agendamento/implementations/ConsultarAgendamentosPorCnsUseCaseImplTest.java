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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultarAgendamentosPorCnsUseCaseImplTest {

    @Mock
    private AgendamentoDomainService agendamentoDomainService;

    @InjectMocks
    private ConsultarAgendamentosPorCnsUseCaseImpl useCase;

    private List<AgendamentoDomain> agendamentos;

    @BeforeEach
    void setUp() {
        StatusConsultaDomain statusConsulta = new StatusConsultaDomain(1L, "AGENDADO");
        StatusNotificacaoDomain statusNotificacao = new StatusNotificacaoDomain(1L, "NAO_ENVIADA");

        agendamentos = Arrays.asList(
                new AgendamentoDomain(1L, "123456789012345", OffsetDateTime.now(), "CARDIOLOGIA", "CRM-SP-123456", "1234567", statusConsulta, statusNotificacao),
                new AgendamentoDomain(2L, "123456789012345", OffsetDateTime.now().plusDays(1), "ORTOPEDIA", "CRM-SP-654321", "7654321", statusConsulta, statusNotificacao)
        );
    }

    @Test
    void shouldConsultarAgendamentosPorCns() {
        when(agendamentoDomainService.buscarTodosAgendamentosPorCns("123456789012345")).thenReturn(agendamentos);

        List<AgendamentoDomain> result = useCase.consultarAgendamentosPorCns("123456789012345");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("123456789012345", result.get(0).getCns());
        assertEquals("123456789012345", result.get(1).getCns());
        verify(agendamentoDomainService, times(1)).buscarTodosAgendamentosPorCns("123456789012345");
    }

    @Test
    void shouldThrowExceptionWhenNoAgendamentosFound() {
        when(agendamentoDomainService.buscarTodosAgendamentosPorCns("000000000000000")).thenReturn(List.of());
        assertThrows(AgendamentoNaoEncontradoException.class, () -> useCase.consultarAgendamentosPorCns("000000000000000"));
        verify(agendamentoDomainService, times(1)).buscarTodosAgendamentosPorCns("000000000000000");
    }
}
