package com.fiap.agendamento.domain.domain.service.implementations;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.domain.exception.StatusConsultaExistenteException;
import com.fiap.agendamento.domain.exception.StatusConsultaNaoEncontradoException;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatusConsultaDomainServiceImplTest {

    @Mock
    private StatusConsultaGateway statusConsultaGateway;

    @InjectMocks
    private StatusConsultaDomainServiceImpl domainService;

    private StatusConsultaDomain statusConsultaDomain;

    @BeforeEach
    void setUp() {
        statusConsultaDomain = new StatusConsultaDomain(1L, "COMPARECEU");
    }

    @Test
    void shouldBuscarStatusConsultaDomainPorId() {
        when(statusConsultaGateway.buscarStatusConsultaPorId(1L)).thenReturn(Optional.of(statusConsultaDomain));

        StatusConsultaDomain result = domainService.buscarStatusConsultaDomainPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("COMPARECEU", result.getStatus());
        verify(statusConsultaGateway, times(1)).buscarStatusConsultaPorId(1L);
    }

    @Test
    void shouldThrowExceptionWhenStatusConsultaNotFound() {
        when(statusConsultaGateway.buscarStatusConsultaPorId(999L)).thenReturn(Optional.empty());

        assertThrows(StatusConsultaNaoEncontradoException.class, () -> {
            domainService.buscarStatusConsultaDomainPorId(999L);
        });
    }

    @Test
    void shouldValidateStatusWhenNotExists() {
        StatusConsultaDomain newStatus = new StatusConsultaDomain(null, "NOVO_STATUS");
        when(statusConsultaGateway.buscarStatusConsultaPorDescricao("NOVO_STATUS")).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> {
            domainService.validarExistenciaStatusPorDescricao(newStatus);
        });
    }

    @Test
    void shouldValidateStatusWhenExistsWithSameId() {
        StatusConsultaDomain existingStatus = new StatusConsultaDomain(1L, "COMPARECEU");
        when(statusConsultaGateway.buscarStatusConsultaPorDescricao("COMPARECEU")).thenReturn(Optional.of(existingStatus));

        assertDoesNotThrow(() -> {
            domainService.validarExistenciaStatusPorDescricao(existingStatus);
        });
    }

    @Test
    void shouldThrowExceptionWhenStatusExistsWithDifferentId() {
        StatusConsultaDomain newStatus = new StatusConsultaDomain(2L, "COMPARECEU");
        StatusConsultaDomain existingStatus = new StatusConsultaDomain(1L, "COMPARECEU");
        when(statusConsultaGateway.buscarStatusConsultaPorDescricao("COMPARECEU")).thenReturn(Optional.of(existingStatus));

        assertThrows(StatusConsultaExistenteException.class, () -> {
            domainService.validarExistenciaStatusPorDescricao(newStatus);
        });
    }
}
