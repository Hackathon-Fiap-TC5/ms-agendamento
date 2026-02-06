package com.fiap.agendamento.entrypoint.controllers;

import com.fiap.agendamento.application.usecase.CriarAgendamentoUseCase;
import com.fiap.agendamento.application.usecase.RegistrarConfirmacaoAgendamentoUseCase;
import com.fiap.agendamento.application.usecase.RegistrarStatusConsultaUseCase;
import com.fiap.agendamento.domain.enums.StatusConsultaEnum;
import com.fiap.agendamento.domain.enums.StatusNotificacaoEnum;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.entrypoint.controllers.presenter.AgendamentoPresenter;
import com.fiap.agendamentoDomain.AgendamentosApi;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusConsultaRequestDto;
import com.fiap.agendamentoDomain.gen.model.ConfirmacaoAgendamentoRequestDto;
import com.fiap.agendamentoDomain.gen.model.CriarAgendamentoRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
public class AgendamentosController implements AgendamentosApi {

    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final RegistrarConfirmacaoAgendamentoUseCase registrarConfirmacaoAgendamentoUseCase;
    private final RegistrarStatusConsultaUseCase registrarStatusConsultaUseCase;

    public AgendamentosController(CriarAgendamentoUseCase criarAgendamentoUseCase,
                                  RegistrarConfirmacaoAgendamentoUseCase registrarConfirmacaoAgendamentoUseCase,
                                  RegistrarStatusConsultaUseCase registrarStatusConsultaUseCase) {
        this.criarAgendamentoUseCase = criarAgendamentoUseCase;
        this.registrarConfirmacaoAgendamentoUseCase = registrarConfirmacaoAgendamentoUseCase;
        this.registrarStatusConsultaUseCase = registrarStatusConsultaUseCase;
    }

    @Override
    public ResponseEntity<Void> _criarAgendamento(CriarAgendamentoRequestDto criarAgendamentoRequestDto) {
        AgendamentoDomain agendamentoDomain = AgendamentoPresenter.toAgendamentoDomain(criarAgendamentoRequestDto);
        criarAgendamentoUseCase.criarAgendamento(agendamentoDomain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> _registrarConfirmacaoAgendamento(Long idAgendamento, ConfirmacaoAgendamentoRequestDto confirmacaoAgendamentoRequestDto) {
        AgendamentoDomain agendamentoDomain = new AgendamentoDomain();
        agendamentoDomain.setStatusNotificacaoEnum(StatusNotificacaoEnum.fromStatus(confirmacaoAgendamentoRequestDto.getStatusNotificacao()));
        registrarConfirmacaoAgendamentoUseCase.registrarConfirmacaoAgendamento(idAgendamento, agendamentoDomain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> _registrarStatusConsulta(Long idAgendamento, AtualizarStatusConsultaRequestDto atualizarStatusConsultaRequestDto) {
        AgendamentoDomain agendamentoDomain = new AgendamentoDomain();
        agendamentoDomain.setStatusConsultaEnum(StatusConsultaEnum.fromStatus(atualizarStatusConsultaRequestDto.getStatusConsulta()));
        registrarStatusConsultaUseCase.registrarStatusConsulta(idAgendamento, agendamentoDomain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
