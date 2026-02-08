package com.fiap.agendamento.application.usecase.status.notificacao.implementation;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarStatusNotificacaoUseCaseImplTest {

    @Mock
    private StatusNotificacaoGateway statusNotificacaoGateway;

    @InjectMocks
    private ListarStatusNotificacaoUseCaseImpl useCase;

    private List<StatusNotificacaoDomain> statusList;

    @BeforeEach
    void setUp() {
        statusList = Arrays.asList(
                new StatusNotificacaoDomain(1L, "CONFIRMOU_48H_ANTECEDENCIA"),
                new StatusNotificacaoDomain(2L, "CONFIRMOU_24H_ANTECEDENCIA"),
                new StatusNotificacaoDomain(3L, "NAO_ENVIAR")
        );
        when(statusNotificacaoGateway.buscarTodosOsStatus()).thenReturn(statusList);
    }

    @Test
    void shouldListStatusNotificacao() {
        List<StatusNotificacaoDomain> result = useCase.listarStatusConsultas();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("CONFIRMOU_48H_ANTECEDENCIA", result.get(0).getStatus());
        verify(statusNotificacaoGateway, times(1)).buscarTodosOsStatus();
    }
}
