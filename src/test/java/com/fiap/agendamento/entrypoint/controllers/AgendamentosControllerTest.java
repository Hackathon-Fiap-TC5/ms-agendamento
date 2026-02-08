package com.fiap.agendamento.entrypoint.controllers;

import com.fiap.agendamento.application.usecase.agendamento.*;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamentoDomain.gen.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendamentosControllerTest {

    @Mock
    private CriarAgendamentoUseCase criarAgendamentoUseCase;

    @Mock
    private AtualizaStatusNotificacaoAgendamentoUseCase atualizaStatusNotificacaoAgendamentoUseCase;

    @Mock
    private AtualizaStatusConsultaUseCase atualizaStatusConsultaUseCase;

    @Mock
    private ConsultarAgendamentoPorIdUseCase consultarAgendamentoPorIdUseCase;

    @Mock
    private ConsultarAgendamentosPorCnsUseCase consultarAgendamentosPorCnsUseCase;

    @Mock
    private CancelarAgendamentoPorIdUseCase cancelarAgendamentoPorIdUseCase;

    @InjectMocks
    private AgendamentosController controller;

    private AgendamentoDomain agendamentoDomain;
    private CriarAgendamentoRequestDto criarAgendamentoRequestDto;

    @BeforeEach
    void setUp() {
        StatusConsultaDomain statusConsulta = new StatusConsultaDomain(1L, "AGENDADO");
        StatusNotificacaoDomain statusNotificacao = new StatusNotificacaoDomain(1L, "NAO_ENVIADA");

        agendamentoDomain = new AgendamentoDomain(
                1L,
                "123456789012345",
                OffsetDateTime.now(),
                "CARDIOLOGIA",
                "CRM-SP-123456",
                "1234567",
                statusConsulta,
                statusNotificacao
        );

        criarAgendamentoRequestDto = new CriarAgendamentoRequestDto();
        criarAgendamentoRequestDto.setCns("123456789012345");
        criarAgendamentoRequestDto.setDataConsulta(OffsetDateTime.now());
        criarAgendamentoRequestDto.setEspecialidade("CARDIOLOGIA");
        criarAgendamentoRequestDto.setCrmMedico("CRM-SP-123456");
        criarAgendamentoRequestDto.setCnesLocal("1234567");
    }

    @Test
    void shouldCreateAgendamento() {
        doNothing().when(criarAgendamentoUseCase).criarAgendamento(any(AgendamentoDomain.class));

        ResponseEntity<Void> response = controller._criarAgendamento(criarAgendamentoRequestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(criarAgendamentoUseCase, times(1)).criarAgendamento(any(AgendamentoDomain.class));
    }

    @Test
    void shouldConsultarAgendamentoPorId() {
        when(consultarAgendamentoPorIdUseCase.consultarAgendamentoPorId(1L)).thenReturn(agendamentoDomain);

        ResponseEntity<AgendamentoResponseDto> response = controller._consultarAgendamentoPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(consultarAgendamentoPorIdUseCase, times(1)).consultarAgendamentoPorId(1L);
    }

    @Test
    void shouldConsultarAgendamentosPorCns() {
        List<AgendamentoDomain> agendamentos = Arrays.asList(agendamentoDomain);
        when(consultarAgendamentosPorCnsUseCase.consultarAgendamentosPorCns("123456789012345"))
                .thenReturn(agendamentos);

        ResponseEntity<List<AgendamentoResponseDto>> response = 
                controller._consultarAgendamentosPorCns("123456789012345");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(consultarAgendamentosPorCnsUseCase, times(1)).consultarAgendamentosPorCns("123456789012345");
    }

    @Test
    void shouldAtualizarStatusConsulta() {
        AtualizarStatusConsultaAgendamentoRequestDto requestDto = new AtualizarStatusConsultaAgendamentoRequestDto();
        requestDto.setStatusConsultaId(2L);
        doNothing().when(atualizaStatusConsultaUseCase).atualizaStatusConsulta(anyLong(), anyLong());

        ResponseEntity<Void> response = controller._atualizarStatusConsultaAgendamento(1L, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(atualizaStatusConsultaUseCase, times(1)).atualizaStatusConsulta(1L, 2L);
    }

    @Test
    void shouldAtualizarStatusNotificacao() {
        AtualizarStatusNotificacaoAgendamentoRequestDto requestDto = new AtualizarStatusNotificacaoAgendamentoRequestDto();
        requestDto.setStatusNotificacaoId(2L);
        doNothing().when(atualizaStatusNotificacaoAgendamentoUseCase).atualizaStatusNotificacao(anyLong(), anyLong());

        ResponseEntity<Void> response = controller._atualizarStatusNotificacaoAgendamento(1L, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(atualizaStatusNotificacaoAgendamentoUseCase, times(1)).atualizaStatusNotificacao(1L, 2L);
    }

    @Test
    void shouldCancelarAgendamento() {
        doNothing().when(cancelarAgendamentoPorIdUseCase).cancelarAgendamentoPorId(anyLong());

        ResponseEntity<Void> response = controller._cancelaAgendamentoPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cancelarAgendamentoPorIdUseCase, times(1)).cancelarAgendamentoPorId(1L);
    }
}
