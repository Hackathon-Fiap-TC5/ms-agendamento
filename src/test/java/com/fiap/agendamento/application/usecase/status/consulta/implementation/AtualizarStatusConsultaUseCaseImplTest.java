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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarStatusConsultaUseCaseImplTest {

    @Mock
    private StatusConsultaGateway statusConsultaGateway;

    @Mock
    private StatusConsultaDomainService statusConsultaDomainService;

    @InjectMocks
    private AtualizarStatusConsultaUseCaseImpl useCase;

    private StatusConsultaDomain existingStatus;
    private StatusConsultaDomain updateStatus;

    @BeforeEach
    void setUp() {
        existingStatus = new StatusConsultaDomain(1L, "COMPARECEU");
        updateStatus = new StatusConsultaDomain(1L, "FALTOU");

        when(statusConsultaDomainService.buscarStatusConsultaDomainPorId(1L)).thenReturn(existingStatus);
        doNothing().when(statusConsultaDomainService).validarExistenciaStatusPorDescricao(any(StatusConsultaDomain.class));
        doNothing().when(statusConsultaGateway).atualizarStatusConsulta(any(StatusConsultaDomain.class));
    }

    @Test
    void shouldUpdateStatusConsulta() {
        useCase.atualizarStatusConsulta(1L, updateStatus);

        verify(statusConsultaDomainService, times(1)).buscarStatusConsultaDomainPorId(1L);
        verify(statusConsultaDomainService, times(1)).validarExistenciaStatusPorDescricao(updateStatus);
        verify(statusConsultaGateway, times(1)).atualizarStatusConsulta(any(StatusConsultaDomain.class));
        assertEquals("FALTOU", existingStatus.getStatus());
    }
}
