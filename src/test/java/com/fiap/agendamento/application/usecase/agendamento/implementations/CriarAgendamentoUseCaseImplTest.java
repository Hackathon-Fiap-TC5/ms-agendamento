package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
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

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarAgendamentoUseCaseImplTest {

    @Mock
    private AgendamentoDomainService agendamentoDomainService;

    @Mock
    private StatusConsultaDomainService statusConsultaDomainService;

    @Mock
    private StatusNotificacaoDomainService statusNotificacaoDomainService;

    @Mock
    private AgendamentoPublisher agendamentoPublisher;

    @InjectMocks
    private CriarAgendamentoUseCaseImpl useCase;

    private AgendamentoDomain agendamentoDomain;
    private StatusConsultaDomain statusConsultaDomain;
    private StatusNotificacaoDomain statusNotificacaoDomain;

    @BeforeEach
    void setUp() {
        statusConsultaDomain = new StatusConsultaDomain(1L, "AGENDADO");
        statusNotificacaoDomain = new StatusNotificacaoDomain(1L, "NAO_ENVIADA");

        agendamentoDomain = new AgendamentoDomain();
        agendamentoDomain.setCns("123456789012345");
        agendamentoDomain.setDataConsulta(OffsetDateTime.now());
        agendamentoDomain.setEspecialidade("CARDIOLOGIA");
        agendamentoDomain.setCrmMedico("CRM-SP-123456");
        agendamentoDomain.setCnesLocal("1234567");

        when(agendamentoDomainService.criarOuAtualizarAgendamento(any(AgendamentoDomain.class)))
                .thenAnswer(invocation -> {
                    AgendamentoDomain domain = invocation.getArgument(0);
                    domain.setId(1L); // simula persistência
                    return domain;
                });

        when(statusConsultaDomainService.buscarStatusConsultaDomainPorId(1L))
                .thenReturn(statusConsultaDomain);

        when(statusNotificacaoDomainService.buscarStatusNotificacaoDomainPorId(1L))
                .thenReturn(statusNotificacaoDomain);
    }

    @Test
    void shouldCreateAgendamentoWithDefaultStatuses() {
        doNothing().when(agendamentoPublisher)
                .publisher(any(AgendamentoMessageEvent.class));

        useCase.criarAgendamento(agendamentoDomain);

        ArgumentCaptor<AgendamentoDomain> agendamentoCaptor =
                ArgumentCaptor.forClass(AgendamentoDomain.class);

        verify(agendamentoDomainService, times(1))
                .criarOuAtualizarAgendamento(agendamentoCaptor.capture());

        AgendamentoDomain savedAgendamento = agendamentoCaptor.getValue();

        assertEquals(statusConsultaDomain, savedAgendamento.getStatusConsultaDomain());
        assertEquals(statusNotificacaoDomain, savedAgendamento.getStatusNotificacaoDomain());

        ArgumentCaptor<AgendamentoMessageEvent> eventCaptor =
                ArgumentCaptor.forClass(AgendamentoMessageEvent.class);

        verify(agendamentoPublisher, times(1))
                .publisher(eventCaptor.capture());

        AgendamentoMessageEvent event = eventCaptor.getValue();

        assertEquals(1L, event.getIdAgendamento());
        assertEquals("123456789012345", event.getCns());
        assertEquals(statusConsultaDomain, event.getStatusConsultaDomain());
        assertEquals(statusNotificacaoDomain, event.getStatusNotificacaoDomain());
        assertNotNull(event.getDataEvento());
    }
}
