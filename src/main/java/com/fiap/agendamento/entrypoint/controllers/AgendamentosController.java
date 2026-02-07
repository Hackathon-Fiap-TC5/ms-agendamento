package com.fiap.agendamento.entrypoint.controllers;

import com.fiap.agendamento.application.usecase.agendamento.ConsultarAgendamentoPorIdUseCase;
import com.fiap.agendamento.application.usecase.agendamento.ConsultarAgendamentosPorCnsUseCase;
import com.fiap.agendamento.application.usecase.agendamento.CriarAgendamentoUseCase;
import com.fiap.agendamento.application.usecase.agendamento.AtualizaStatusNotificacaoAgendamentoUseCase;
import com.fiap.agendamento.application.usecase.agendamento.AtualizaStatusConsultaUseCase;
import com.fiap.agendamento.application.usecase.agendamento.CancelarAgendamentoPorIdUseCase;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.entrypoint.controllers.presenter.AgendamentoPresenter;
import com.fiap.agendamentoDomain.AgendamentosApi;
import com.fiap.agendamentoDomain.gen.model.AgendamentoResponseDto;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusConsultaAgendamentoRequestDto;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusNotificacaoAgendamentoRequestDto;
import com.fiap.agendamentoDomain.gen.model.CriarAgendamentoRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class AgendamentosController implements AgendamentosApi {

    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final AtualizaStatusNotificacaoAgendamentoUseCase atualizaStatusNotificacaoAgendamentoUseCase;
    private final AtualizaStatusConsultaUseCase atualizaStatusConsultaUseCase;
    private final ConsultarAgendamentoPorIdUseCase consultarAgendamentoPorIdUseCase;
    private final ConsultarAgendamentosPorCnsUseCase consultarAgendamentoconsultarAgendamentosPorCns;
    private final CancelarAgendamentoPorIdUseCase cancelarAgendamentoPorIdUseCase;

    public AgendamentosController(CriarAgendamentoUseCase criarAgendamentoUseCase,
                                  AtualizaStatusNotificacaoAgendamentoUseCase atualizaStatusNotificacaoAgendamentoUseCase,
                                  AtualizaStatusConsultaUseCase atualizaStatusConsultaUseCase,
                                  ConsultarAgendamentoPorIdUseCase consultarAgendamentoPorIdUseCase,
                                  ConsultarAgendamentosPorCnsUseCase consultarAgendamentoconsultarAgendamentosPorCns,
                                  CancelarAgendamentoPorIdUseCase cancelarAgendamentoPorIdUseCase) {
        this.criarAgendamentoUseCase = criarAgendamentoUseCase;
        this.atualizaStatusNotificacaoAgendamentoUseCase = atualizaStatusNotificacaoAgendamentoUseCase;
        this.atualizaStatusConsultaUseCase = atualizaStatusConsultaUseCase;
        this.consultarAgendamentoPorIdUseCase = consultarAgendamentoPorIdUseCase;
        this.consultarAgendamentoconsultarAgendamentosPorCns = consultarAgendamentoconsultarAgendamentosPorCns;
        this.cancelarAgendamentoPorIdUseCase = cancelarAgendamentoPorIdUseCase;
    }


    @Override
    public ResponseEntity<AgendamentoResponseDto> _consultarAgendamentoPorId(Long idAgendamento) {
        AgendamentoDomain domain = consultarAgendamentoPorIdUseCase.consultarAgendamentoPorId(idAgendamento);
        AgendamentoResponseDto agendamentoResponseDto = AgendamentoPresenter.toAgendamentoResponseDto(domain);
        return ResponseEntity.ok(agendamentoResponseDto);
    }

    @Override
    public ResponseEntity<List<AgendamentoResponseDto>> _consultarAgendamentosPorCns(String cns) {
        List<AgendamentoDomain> agendamentoDomains = consultarAgendamentoconsultarAgendamentosPorCns.consultarAgendamentosPorCns(cns);
        List<AgendamentoResponseDto> listAgendamentoResponseDto = AgendamentoPresenter.toListAgendamentoResponseDto(agendamentoDomains);
        return ResponseEntity.ok(listAgendamentoResponseDto);
    }


    @Override
    public ResponseEntity<Void> _criarAgendamento(CriarAgendamentoRequestDto criarAgendamentoRequestDto) {
        AgendamentoDomain agendamentoDomain = AgendamentoPresenter.toAgendamentoDomain(criarAgendamentoRequestDto);
        criarAgendamentoUseCase.criarAgendamento(agendamentoDomain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> _atualizarStatusConsultaAgendamento(Long idAgendamento, AtualizarStatusConsultaAgendamentoRequestDto atualizarStatusConsultaAgendamentoRequestDto) {
        atualizaStatusConsultaUseCase.atualizaStatusConsulta(idAgendamento, atualizarStatusConsultaAgendamentoRequestDto.getStatusConsultaId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> _atualizarStatusNotificacaoAgendamento(Long idAgendamento, AtualizarStatusNotificacaoAgendamentoRequestDto atualizarStatusNotificacaoAgendamentoRequestDto) {
        atualizaStatusNotificacaoAgendamentoUseCase.atualizaStatusNotificacao(idAgendamento, atualizarStatusNotificacaoAgendamentoRequestDto.getStatusNotificacaoId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> _cancelaAgendamentoPorId(Long idAgendamento) {
        cancelarAgendamentoPorIdUseCase.cancelarAgendamentoPorId(idAgendamento);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}