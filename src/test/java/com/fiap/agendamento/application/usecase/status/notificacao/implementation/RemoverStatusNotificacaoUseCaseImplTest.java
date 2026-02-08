package com.fiap.agendamento.application.usecase.status.notificacao.implementation;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoverStatusNotificacaoUseCaseImplTest {

    @Mock
    private StatusNotificacaoGateway statusNotificacaoGateway;

    @Mock
    private StatusNotificacaoDomainService statusNotificacaoDomainService;

    @InjectMocks
    private RemoverStatusNotificacaoUseCaseImpl useCase;

    private StatusNotificacaoDomain statusNotificacaoDomain;

    @BeforeEach
    void setUp() {
        statusNotificacaoDomain = new StatusNotificacaoDomain(1L, "CONFIRMOU_48H_ANTECEDENCIA");
        when(statusNotificacaoDomainService.buscarStatusNotificacaoDomainPorId(1L)).thenReturn(statusNotificacaoDomain);
        doNothing().when(statusNotificacaoGateway).removerStatus(any(StatusNotificacaoDomain.class));
    }

    @Test
    void shouldRemoveStatusNotificacao() {
        useCase.removerStatusNotificacao(1L);

        verify(statusNotificacaoDomainService, times(1)).buscarStatusNotificacaoDomainPorId(1L);
        verify(statusNotificacaoGateway, times(1)).removerStatus(statusNotificacaoDomain);
    }
}
