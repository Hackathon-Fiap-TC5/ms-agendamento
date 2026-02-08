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
class CriarStatusNotificacaoUseCaseImplTest {

    @Mock
    private StatusNotificacaoGateway statusNotificacaoGateway;

    @Mock
    private StatusNotificacaoDomainService statusNotificacaoDomainService;

    @InjectMocks
    private CriarStatusNotificacaoUseCaseImpl useCase;

    private StatusNotificacaoDomain statusNotificacaoDomain;

    @BeforeEach
    void setUp() {
        statusNotificacaoDomain = new StatusNotificacaoDomain(null, "CONFIRMOU_48H_ANTECEDENCIA");
        doNothing().when(statusNotificacaoDomainService).validarExistenciaStatusPorDescricao(any(StatusNotificacaoDomain.class));
        doNothing().when(statusNotificacaoGateway).criarStatusNotificacao(any(StatusNotificacaoDomain.class));
    }

    @Test
    void shouldCreateStatusNotificacao() {
        useCase.criarStatusNotificacao(statusNotificacaoDomain);

        verify(statusNotificacaoDomainService, times(1)).validarExistenciaStatusPorDescricao(statusNotificacaoDomain);
        verify(statusNotificacaoGateway, times(1)).criarStatusNotificacao(statusNotificacaoDomain);
    }
}
