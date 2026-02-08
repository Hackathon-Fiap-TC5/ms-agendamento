package com.fiap.agendamento.application.usecase.status.consulta.implementation;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoverStatusConsultaUseCaseImplTest {

    @Mock
    private StatusConsultaGateway statusConsultaGateway;

    @Mock
    private StatusConsultaDomainService statusConsultaDomainService;

    @InjectMocks
    private RemoverStatusConsultaUseCaseImpl useCase;

    private StatusConsultaDomain statusConsultaDomain;

    @BeforeEach
    void setUp() {
        statusConsultaDomain = new StatusConsultaDomain(1L, "COMPARECEU");
        when(statusConsultaDomainService.buscarStatusConsultaDomainPorId(1L)).thenReturn(statusConsultaDomain);
        doNothing().when(statusConsultaGateway).removerStatus(any(StatusConsultaDomain.class));
    }

    @Test
    void shouldRemoveStatusConsulta() {
        useCase.removerStatusConsulta(1L);

        verify(statusConsultaDomainService, times(1)).buscarStatusConsultaDomainPorId(1L);
        verify(statusConsultaGateway, times(1)).removerStatus(statusConsultaDomain);
    }
}
