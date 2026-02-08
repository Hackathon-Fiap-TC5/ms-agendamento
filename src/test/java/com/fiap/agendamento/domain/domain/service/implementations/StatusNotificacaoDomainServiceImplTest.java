package com.fiap.agendamento.domain.domain.service.implementations;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.domain.exception.StatusConsultaNaoEncontradoException;
import com.fiap.agendamento.domain.exception.StatusNotificacaoExistenteException;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
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
class StatusNotificacaoDomainServiceImplTest {

    @Mock
    private StatusNotificacaoGateway statusNotificacaoGateway;

    @InjectMocks
    private StatusNotificacaoDomainServiceImpl domainService;

    private StatusNotificacaoDomain statusNotificacaoDomain;

    @BeforeEach
    void setUp() {
        statusNotificacaoDomain = new StatusNotificacaoDomain(1L, "CONFIRMOU_48H_ANTECEDENCIA");
    }

    @Test
    void shouldBuscarStatusNotificacaoDomainPorId() {
        when(statusNotificacaoGateway.buscarStatusNotificacaoPorId(1L)).thenReturn(Optional.of(statusNotificacaoDomain));

        StatusNotificacaoDomain result = domainService.buscarStatusNotificacaoDomainPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("CONFIRMOU_48H_ANTECEDENCIA", result.getStatus());
        verify(statusNotificacaoGateway, times(1)).buscarStatusNotificacaoPorId(1L);
    }

    @Test
    void shouldThrowExceptionWhenStatusNotificacaoNotFound() {
        when(statusNotificacaoGateway.buscarStatusNotificacaoPorId(999L)).thenReturn(Optional.empty());

        assertThrows(StatusConsultaNaoEncontradoException.class, () -> {
            domainService.buscarStatusNotificacaoDomainPorId(999L);
        });
    }

    @Test
    void shouldValidateStatusWhenNotExists() {
        StatusNotificacaoDomain newStatus = new StatusNotificacaoDomain(null, "NOVO_STATUS");
        when(statusNotificacaoGateway.buscarStatusNotificacaoPorDescricao("NOVO_STATUS")).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> {
            domainService.validarExistenciaStatusPorDescricao(newStatus);
        });
    }

    @Test
    void shouldValidateStatusWhenExistsWithSameId() {
        StatusNotificacaoDomain existingStatus = new StatusNotificacaoDomain(1L, "CONFIRMOU_48H_ANTECEDENCIA");
        when(statusNotificacaoGateway.buscarStatusNotificacaoPorDescricao("CONFIRMOU_48H_ANTECEDENCIA")).thenReturn(Optional.of(existingStatus));

        assertDoesNotThrow(() -> {
            domainService.validarExistenciaStatusPorDescricao(existingStatus);
        });
    }

    @Test
    void shouldThrowExceptionWhenStatusExistsWithDifferentId() {
        StatusNotificacaoDomain newStatus = new StatusNotificacaoDomain(2L, "CONFIRMOU_48H_ANTECEDENCIA");
        StatusNotificacaoDomain existingStatus = new StatusNotificacaoDomain(1L, "CONFIRMOU_48H_ANTECEDENCIA");
        when(statusNotificacaoGateway.buscarStatusNotificacaoPorDescricao("CONFIRMOU_48H_ANTECEDENCIA")).thenReturn(Optional.of(existingStatus));

        assertThrows(StatusNotificacaoExistenteException.class, () -> {
            domainService.validarExistenciaStatusPorDescricao(newStatus);
        });
    }
}
