package com.fiap.agendamento.domain.domain.service.implementations;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.domain.exception.AgendamentoNaoEncontradoException;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamento.infrastructure.queue.publisher.AgendamentoPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendamentoDomainServiceImplTest {

    @Mock
    private AgendamentoGateway agendamentoGateway;

    @Mock
    private AgendamentoPublisher publisher;

    @InjectMocks
    private AgendamentoDomainServiceImpl domainService;

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
    void shouldBuscarAgendamentoDomainPorId() {
        when(agendamentoGateway.buscarAgendamentoPorId(1L)).thenReturn(Optional.of(agendamentoDomain));

        AgendamentoDomain result = domainService.buscarAgendamentoDomainPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("123456789012345", result.getCns());
        verify(agendamentoGateway, times(1)).buscarAgendamentoPorId(1L);
    }

    @Test
    void shouldThrowExceptionWhenAgendamentoNotFound() {
        when(agendamentoGateway.buscarAgendamentoPorId(999L)).thenReturn(Optional.empty());

        assertThrows(AgendamentoNaoEncontradoException.class, () -> {
            domainService.buscarAgendamentoDomainPorId(999L);
        });
    }

    @Test
    void shouldBuscarTodosAgendamentosPorCns() {
        List<AgendamentoDomain> agendamentos = Arrays.asList(agendamentoDomain);
        when(agendamentoGateway.buscarTodosAgendamentosPorCns("123456789012345")).thenReturn(agendamentos);

        List<AgendamentoDomain> result = domainService.buscarTodosAgendamentosPorCns("123456789012345");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("123456789012345", result.get(0).getCns());
        verify(agendamentoGateway, times(1)).buscarTodosAgendamentosPorCns("123456789012345");
    }

    @Test
    void shouldCriarOuAtualizarAgendamento() {
        doNothing().when(agendamentoGateway).criarOuAtualizarAgendamento(any(AgendamentoDomain.class));

        domainService.criarOuAtualizarAgendamento(agendamentoDomain);

        verify(agendamentoGateway, times(1)).criarOuAtualizarAgendamento(agendamentoDomain);
    }
}
