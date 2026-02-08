package com.fiap.agendamento.entrypoint.controllers;

import com.fiap.agendamento.application.usecase.status.consulta.*;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusConsultaRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusConsultaRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusConsultaResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatusConsultaControllerTest {

    @Mock
    private AtualizarStatusConsultaUseCase atualizarStatusConsultaUseCase;

    @Mock
    private CriarStatusConsultaUseCase criarStatusConsultaUseCase;

    @Mock
    private ListarStatusConsultaUseCase listarStatusConsultaUseCase;

    @Mock
    private RemoverStatusConsultaUseCase removerStatusConsultaUseCase;

    @InjectMocks
    private StatusConsultaController controller;

    private StatusConsultaDomain statusConsultaDomain;
    private StatusConsultaRequestDto requestDto;

    @BeforeEach
    void setUp() {
        statusConsultaDomain = new StatusConsultaDomain(1L, "COMPARECEU");
        
        requestDto = new StatusConsultaRequestDto();
        requestDto.setStatus("COMPARECEU");
    }

    @Test
    void shouldCreateStatusConsulta() {
        doNothing().when(criarStatusConsultaUseCase).criarStatusConsulta(any(StatusConsultaDomain.class));

        ResponseEntity<Void> response = controller._criarStatusConsulta(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(criarStatusConsultaUseCase, times(1)).criarStatusConsulta(any(StatusConsultaDomain.class));
    }

    @Test
    void shouldListStatusConsulta() {
        List<StatusConsultaDomain> statusList = Arrays.asList(
                new StatusConsultaDomain(1L, "COMPARECEU"),
                new StatusConsultaDomain(2L, "FALTOU")
        );
        when(listarStatusConsultaUseCase.listarStatusConsultas()).thenReturn(statusList);

        ResponseEntity<List<StatusConsultaResponseDto>> response = controller._listarStatusConsulta();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(listarStatusConsultaUseCase, times(1)).listarStatusConsultas();
    }

    @Test
    void shouldUpdateStatusConsulta() {
        AtualizarStatusConsultaRequestDto updateDto = new AtualizarStatusConsultaRequestDto();
        updateDto.setStatus("FALTOU");
        doNothing().when(atualizarStatusConsultaUseCase).atualizarStatusConsulta(anyLong(), any(StatusConsultaDomain.class));

        ResponseEntity<Void> response = controller._atualizarStatusConsulta(1L, updateDto);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(atualizarStatusConsultaUseCase, times(1)).atualizarStatusConsulta(eq(1L), any(StatusConsultaDomain.class));
    }

    @Test
    void shouldRemoveStatusConsulta() {
        doNothing().when(removerStatusConsultaUseCase).removerStatusConsulta(anyLong());

        ResponseEntity<Void> response = controller._removerStatusConsulta(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(removerStatusConsultaUseCase, times(1)).removerStatusConsulta(1L);
    }
}
