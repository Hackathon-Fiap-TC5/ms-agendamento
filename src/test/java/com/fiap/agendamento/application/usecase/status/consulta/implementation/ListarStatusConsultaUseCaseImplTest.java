package com.fiap.agendamento.application.usecase.status.consulta.implementation;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
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
class ListarStatusConsultaUseCaseImplTest {

    @Mock
    private StatusConsultaGateway statusConsultaGateway;

    @InjectMocks
    private ListarStatusConsultaUseCaseImpl useCase;

    private List<StatusConsultaDomain> statusList;

    @BeforeEach
    void setUp() {
        statusList = Arrays.asList(
                new StatusConsultaDomain(1L, "COMPARECEU"),
                new StatusConsultaDomain(2L, "FALTOU"),
                new StatusConsultaDomain(3L, "CANCELADO")
        );
        when(statusConsultaGateway.buscarTodosOsStatus()).thenReturn(statusList);
    }

    @Test
    void shouldListStatusConsulta() {
        List<StatusConsultaDomain> result = useCase.listarStatusConsultas();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("COMPARECEU", result.get(0).getStatus());
        verify(statusConsultaGateway, times(1)).buscarTodosOsStatus();
    }
}
