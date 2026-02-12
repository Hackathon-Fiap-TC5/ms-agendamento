package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.domain.exception.AgendamentoNaoEncontradoException;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamento.infrastructure.producer.payload.AgendamentoMessageEvent;
import com.fiap.agendamento.infrastructure.producer.AgendamentoPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AtualizaStatusNotificacaoAgendamentoUseCaseImplTest {

    @Mock
    private AgendamentoDomainService agendamentoDomainService;

    @Mock
    private StatusNotificacaoDomainService statusNotificacaoDomainService;

    @Mock
    private AgendamentoPublisher agendamentoPublisher;

    @InjectMocks
    private AtualizaStatusNotificacaoAgendamentoUseCaseImpl useCase;

    private AgendamentoDomain agendamentoDomain;
    private StatusNotificacaoDomain novoStatusNotificacao;

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

        novoStatusNotificacao = new StatusNotificacaoDomain(2L, "CONFIRMOU_48H_ANTECEDENCIA");

        when(agendamentoDomainService.buscarAgendamentoDomainPorId(1L)).thenReturn(agendamentoDomain);
        when(statusNotificacaoDomainService.buscarStatusNotificacaoDomainPorId(2L)).thenReturn(novoStatusNotificacao);
        when(agendamentoDomainService.criarOuAtualizarAgendamento(any(AgendamentoDomain.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        doNothing().when(agendamentoPublisher).publisher(any(AgendamentoMessageEvent.class));
    }

    @Test
    void shouldAtualizarStatusNotificacao() {
        useCase.atualizaStatusNotificacao(1L, 2L);

        ArgumentCaptor<AgendamentoDomain> agendamentoCaptor = ArgumentCaptor.forClass(AgendamentoDomain.class);
        verify(agendamentoDomainService, times(1)).criarOuAtualizarAgendamento(agendamentoCaptor.capture());
        
        AgendamentoDomain updatedAgendamento = agendamentoCaptor.getValue();
        assertEquals(novoStatusNotificacao, updatedAgendamento.getStatusNotificacaoDomain());
        
        ArgumentCaptor<AgendamentoMessageEvent> eventCaptor = ArgumentCaptor.forClass(AgendamentoMessageEvent.class);
        verify(agendamentoPublisher, times(1)).publisher(eventCaptor.capture());
        
        AgendamentoMessageEvent event = eventCaptor.getValue();
        assertEquals("123456789012345", event.getCns());
        assertEquals(novoStatusNotificacao, event.getStatusNotificacaoDomain());
    }

    @Test
    void shouldThrowAgendamentoNaoEncontradoExceptionWhenAgendamentoNotFound() {
        when(agendamentoDomainService.buscarAgendamentoDomainPorId(999L))
                .thenThrow(new AgendamentoNaoEncontradoException());

        assertThrows(AgendamentoNaoEncontradoException.class, () ->
                useCase.atualizaStatusNotificacao(999L, 2L));

        verify(agendamentoDomainService, times(1)).buscarAgendamentoDomainPorId(999L);
        verify(agendamentoDomainService, never()).criarOuAtualizarAgendamento(any(AgendamentoDomain.class));
    }
}
