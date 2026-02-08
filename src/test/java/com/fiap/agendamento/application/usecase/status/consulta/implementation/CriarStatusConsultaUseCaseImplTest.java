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
class CriarStatusConsultaUseCaseImplTest {

    @Mock
    private StatusConsultaGateway statusConsultaGateway;

    @Mock
    private StatusConsultaDomainService statusConsultaDomainService;

    @InjectMocks
    private CriarStatusConsultaUseCaseImpl useCase;

    private StatusConsultaDomain statusConsultaDomain;

    @BeforeEach
    void setUp() {
        statusConsultaDomain = new StatusConsultaDomain(null, "COMPARECEU");
        doNothing().when(statusConsultaDomainService).validarExistenciaStatusPorDescricao(any(StatusConsultaDomain.class));
        doNothing().when(statusConsultaGateway).criarStatusConsulta(any(StatusConsultaDomain.class));
    }

    @Test
    void shouldCreateStatusConsulta() {
        useCase.criarStatusConsulta(statusConsultaDomain);

        verify(statusConsultaDomainService, times(1)).validarExistenciaStatusPorDescricao(statusConsultaDomain);
        verify(statusConsultaGateway, times(1)).criarStatusConsulta(statusConsultaDomain);
    }
}
