package com.fiap.agendamento.entrypoint.controllers;

import com.fiap.agendamento.application.usecase.status.notificacao.*;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusNotificacaoRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusNotificacaoRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusNotificacaoResponseDto;
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
class StatusNotificacaoControllerTest {

    @Mock
    private AtualizarStatusNotificacaoUseCase atualizarStatusNotificacaoUseCase;

    @Mock
    private CriarStatusNotificacaoUseCase criarStatusNotificacaoUseCase;

    @Mock
    private ListarStatusNotificacaoUseCase listarStatusNotificacaoUseCase;

    @Mock
    private RemoverStatusNotificacaoUseCase removerStatusNotificacaoUseCase;

    @InjectMocks
    private StatusNotificacaoController controller;

    private StatusNotificacaoDomain statusNotificacaoDomain;
    private StatusNotificacaoRequestDto requestDto;

    @BeforeEach
    void setUp() {
        statusNotificacaoDomain = new StatusNotificacaoDomain(1L, "CONFIRMOU_48H_ANTECEDENCIA");
        
        requestDto = new StatusNotificacaoRequestDto();
        requestDto.setStatus("CONFIRMOU_48H_ANTECEDENCIA");
    }

    @Test
    void shouldCreateStatusNotificacao() {
        doNothing().when(criarStatusNotificacaoUseCase).criarStatusNotificacao(any(StatusNotificacaoDomain.class));

        ResponseEntity<Void> response = controller._criarStatusNotificacao(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(criarStatusNotificacaoUseCase, times(1)).criarStatusNotificacao(any(StatusNotificacaoDomain.class));
    }

    @Test
    void shouldListStatusNotificacao() {
        List<StatusNotificacaoDomain> statusList = Arrays.asList(
                new StatusNotificacaoDomain(1L, "CONFIRMOU_48H_ANTECEDENCIA"),
                new StatusNotificacaoDomain(2L, "CONFIRMOU_24H_ANTECEDENCIA")
        );
        when(listarStatusNotificacaoUseCase.listarStatusConsultas()).thenReturn(statusList);

        ResponseEntity<List<StatusNotificacaoResponseDto>> response = controller._listarStatusNotificacao();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(listarStatusNotificacaoUseCase, times(1)).listarStatusConsultas();
    }

    @Test
    void shouldUpdateStatusNotificacao() {
        AtualizarStatusNotificacaoRequestDto updateDto = new AtualizarStatusNotificacaoRequestDto();
        updateDto.setStatus("CONFIRMOU_24H_ANTECEDENCIA");
        doNothing().when(atualizarStatusNotificacaoUseCase).atualizarStatusNotificacao(anyLong(), any(StatusNotificacaoDomain.class));

        ResponseEntity<Void> response = controller._atualizarStatusNotificacao(1L, updateDto);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(atualizarStatusNotificacaoUseCase, times(1)).atualizarStatusNotificacao(eq(1L), any(StatusNotificacaoDomain.class));
    }

    @Test
    void shouldRemoveStatusNotificacao() {
        doNothing().when(removerStatusNotificacaoUseCase).removerStatusNotificacao(anyLong());

        ResponseEntity<Void> response = controller._removerStatusNotificacao(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(removerStatusNotificacaoUseCase, times(1)).removerStatusNotificacao(1L);
    }
}
