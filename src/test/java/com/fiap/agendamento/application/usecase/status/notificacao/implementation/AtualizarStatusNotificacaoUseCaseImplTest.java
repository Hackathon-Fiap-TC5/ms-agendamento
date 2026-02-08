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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarStatusNotificacaoUseCaseImplTest {

    @Mock
    private StatusNotificacaoGateway statusNotificacaoGateway;

    @Mock
    private StatusNotificacaoDomainService statusNotificacaoDomainService;

    @InjectMocks
    private AtualizarStatusNotificacaoUseCaseImpl useCase;

    private StatusNotificacaoDomain existingStatus;
    private StatusNotificacaoDomain updateStatus;

    @BeforeEach
    void setUp() {
        existingStatus = new StatusNotificacaoDomain(1L, "CONFIRMOU_48H_ANTECEDENCIA");
        updateStatus = new StatusNotificacaoDomain(1L, "CONFIRMOU_24H_ANTECEDENCIA");

        when(statusNotificacaoDomainService.buscarStatusNotificacaoDomainPorId(1L)).thenReturn(existingStatus);
        doNothing().when(statusNotificacaoDomainService).validarExistenciaStatusPorDescricao(any(StatusNotificacaoDomain.class));
        doNothing().when(statusNotificacaoGateway).atualizarStatusNotificacao(any(StatusNotificacaoDomain.class));
    }

    @Test
    void shouldUpdateStatusNotificacao() {
        useCase.atualizarStatusNotificacao(1L, updateStatus);

        verify(statusNotificacaoDomainService, times(1)).buscarStatusNotificacaoDomainPorId(1L);
        verify(statusNotificacaoDomainService, times(1)).validarExistenciaStatusPorDescricao(updateStatus);
        verify(statusNotificacaoGateway, times(1)).atualizarStatusNotificacao(any(StatusNotificacaoDomain.class));
        assertEquals("CONFIRMOU_24H_ANTECEDENCIA", existingStatus.getStatus());
    }
}
