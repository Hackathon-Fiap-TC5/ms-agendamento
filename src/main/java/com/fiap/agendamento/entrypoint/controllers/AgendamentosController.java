package com.fiap.agendamento.entrypoint.controllers;

import com.fiap.agendamento.application.usecase.agendamento.ConsultarAgendamentoPorIdUseCase;
import com.fiap.agendamento.application.usecase.agendamento.ConsultarAgendamentosPorCnsUseCase;
import com.fiap.agendamento.application.usecase.agendamento.CriarAgendamentoUseCase;
import com.fiap.agendamento.application.usecase.agendamento.RegistrarConfirmacaoAgendamentoUseCase;
import com.fiap.agendamento.application.usecase.agendamento.RegistrarStatusConsultaUseCase;
import com.fiap.agendamento.domain.enums.StatusConsultaEnum;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.entrypoint.controllers.presenter.AgendamentoPresenter;
import com.fiap.agendamentoDomain.AgendamentosApi;
import com.fiap.agendamentoDomain.gen.model.AgendamentoResponseDto;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusConsultaAgendamentoRequestDto;
import com.fiap.agendamentoDomain.gen.model.ConfirmacaoAgendamentoRequestDto;
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
    private final RegistrarConfirmacaoAgendamentoUseCase registrarConfirmacaoAgendamentoUseCase;
    private final RegistrarStatusConsultaUseCase registrarStatusConsultaUseCase;
    private final ConsultarAgendamentoPorIdUseCase consultarAgendamentoPorIdUseCase;
    private final ConsultarAgendamentosPorCnsUseCase consultarAgendamentoconsultarAgendamentosPorCns;

    public AgendamentosController(CriarAgendamentoUseCase criarAgendamentoUseCase,
                                  RegistrarConfirmacaoAgendamentoUseCase registrarConfirmacaoAgendamentoUseCase,
                                  RegistrarStatusConsultaUseCase registrarStatusConsultaUseCase,
                                  ConsultarAgendamentoPorIdUseCase consultarAgendamentoPorIdUseCase,
                                  ConsultarAgendamentosPorCnsUseCase consultarAgendamentoconsultarAgendamentosPorCns) {
        this.criarAgendamentoUseCase = criarAgendamentoUseCase;
        this.registrarConfirmacaoAgendamentoUseCase = registrarConfirmacaoAgendamentoUseCase;
        this.registrarStatusConsultaUseCase = registrarStatusConsultaUseCase;
        this.consultarAgendamentoPorIdUseCase = consultarAgendamentoPorIdUseCase;
        this.consultarAgendamentoconsultarAgendamentosPorCns = consultarAgendamentoconsultarAgendamentosPorCns;
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
    public ResponseEntity<Void> _registrarConfirmacaoAgendamento(Long idAgendamento, ConfirmacaoAgendamentoRequestDto confirmacaoAgendamentoRequestDto) {
        AgendamentoDomain agendamentoDomain = AgendamentoPresenter.toBuildAgendamentoDomainStatusNotificacao(confirmacaoAgendamentoRequestDto.getStatusNotificacaoId());
        registrarConfirmacaoAgendamentoUseCase.registrarConfirmacaoAgendamento(idAgendamento, agendamentoDomain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> _registrarStatusConsulta(Long idAgendamento, AtualizarStatusConsultaAgendamentoRequestDto atualizarStatusConsultaAgendamentoRequestDto) {
        AgendamentoDomain agendamentoDomain = AgendamentoPresenter.toBuildAgendamentoDomainStatusConsulta(atualizarStatusConsultaAgendamentoRequestDto.getStatusConsultaId());
        registrarStatusConsultaUseCase.registrarStatusConsulta(idAgendamento, agendamentoDomain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
