package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamento.infrastructure.publisher.payload.AgendamentoMessageEvent;
import com.fiap.agendamento.infrastructure.publisher.AgendamentoPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CancelarAgendamentoPorIdUseCaseImplTest {

    @Mock
    private AgendamentoDomainService agendamentoDomainService;

    @Mock
    private StatusConsultaDomainService statusConsultaDomainService;

    @Mock
    private StatusNotificacaoDomainService statusNotificacaoDomainService;

    @Mock
    private AgendamentoGateway agendamentoGateway;

    @Mock
    private AgendamentoPublisher agendamentoPublisher;

    @InjectMocks
    private CancelarAgendamentoPorIdUseCaseImpl useCase;

    private AgendamentoDomain agendamentoDomain;
    private StatusConsultaDomain statusCancelado;
    private StatusNotificacaoDomain statusNaoEnviar;

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

        statusCancelado = new StatusConsultaDomain(3L, "CANCELADO");
        statusNaoEnviar = new StatusNotificacaoDomain(1L, "NAO_ENVIAR");

        when(agendamentoDomainService.buscarAgendamentoDomainPorId(1L)).thenReturn(agendamentoDomain);
        when(statusConsultaDomainService.buscarStatusConsultaDomainPorId(3L)).thenReturn(statusCancelado);
        when(statusNotificacaoDomainService.buscarStatusNotificacaoDomainPorId(1L)).thenReturn(statusNaoEnviar);
        when(agendamentoGateway.criarOuAtualizarAgendamento(any(AgendamentoDomain.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        doNothing().when(agendamentoPublisher).publisher(any(AgendamentoMessageEvent.class));
    }

    @Test
    void shouldCancelarAgendamento() {
        useCase.cancelarAgendamentoPorId(1L);

        ArgumentCaptor<AgendamentoDomain> agendamentoCaptor = ArgumentCaptor.forClass(AgendamentoDomain.class);
        verify(agendamentoGateway, times(1)).criarOuAtualizarAgendamento(agendamentoCaptor.capture());
        
        AgendamentoDomain canceledAgendamento = agendamentoCaptor.getValue();
        assertEquals(statusCancelado, canceledAgendamento.getStatusConsultaDomain());
        assertEquals(statusNaoEnviar, canceledAgendamento.getStatusNotificacaoDomain());
        
        ArgumentCaptor<AgendamentoMessageEvent> eventCaptor = ArgumentCaptor.forClass(AgendamentoMessageEvent.class);
        verify(agendamentoPublisher, times(1)).publisher(eventCaptor.capture());
        
        AgendamentoMessageEvent event = eventCaptor.getValue();
        assertEquals("123456789012345", event.getCns());
        assertEquals(statusCancelado, event.getStatusConsultaDomain());
        assertEquals(statusNaoEnviar, event.getStatusNotificacaoDomain());
    }
}
