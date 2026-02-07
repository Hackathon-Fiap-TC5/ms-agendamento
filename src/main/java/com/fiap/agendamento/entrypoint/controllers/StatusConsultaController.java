package com.fiap.agendamento.entrypoint.controllers;

import com.fiap.agendamento.application.usecase.status.consulta.AtualizarStatusConsultaUseCase;
import com.fiap.agendamento.application.usecase.status.consulta.CriarStatusConsultaUseCase;
import com.fiap.agendamento.application.usecase.status.consulta.ListarStatusConsultaUseCase;
import com.fiap.agendamento.application.usecase.status.consulta.RemoverStatusConsultaUseCase;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.entrypoint.controllers.presenter.StatusConsultaPresenter;
import com.fiap.agendamentoDomain.StatusConsultaApi;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusConsultaRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusConsultaRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusConsultaResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class StatusConsultaController implements StatusConsultaApi {

    private final AtualizarStatusConsultaUseCase atualizarStatusConsultaUseCase;
    private final CriarStatusConsultaUseCase criarStatusConsultaUseCase;
    private final ListarStatusConsultaUseCase listarStatusConsultaUseCase;
    private final RemoverStatusConsultaUseCase removerStatusConsultaUseCase;

    public StatusConsultaController(AtualizarStatusConsultaUseCase atualizarStatusConsultaUseCase,
                                    CriarStatusConsultaUseCase criarStatusConsultaUseCase,
                                    ListarStatusConsultaUseCase listarStatusConsultaUseCase,
                                    RemoverStatusConsultaUseCase removerStatusConsultaUseCase) {
        this.atualizarStatusConsultaUseCase = atualizarStatusConsultaUseCase;
        this.criarStatusConsultaUseCase = criarStatusConsultaUseCase;
        this.listarStatusConsultaUseCase = listarStatusConsultaUseCase;
        this.removerStatusConsultaUseCase = removerStatusConsultaUseCase;
    }

    @Override
    public ResponseEntity<Void> _atualizarStatusConsulta(Long id, AtualizarStatusConsultaRequestDto atualizarStatusConsultaRequestDto) {
        StatusConsultaDomain domain = StatusConsultaPresenter.toStatusConsultaDomainAtualizar(atualizarStatusConsultaRequestDto);
        atualizarStatusConsultaUseCase.atualizarStatusConsulta(id, domain);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> _criarStatusConsulta(StatusConsultaRequestDto statusConsultaRequestDto) {
        StatusConsultaDomain domain = StatusConsultaPresenter.toStatusConsultaDomain(statusConsultaRequestDto);
        criarStatusConsultaUseCase.criarStatusConsulta(domain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<StatusConsultaResponseDto>> _listarStatusConsulta() {
        List<StatusConsultaDomain> listStatusConsultaDomains = listarStatusConsultaUseCase.listarStatusConsultas();
        List<StatusConsultaResponseDto> listDto = StatusConsultaPresenter.toListStatusConsultaResponseDto(listStatusConsultaDomains);
        return ResponseEntity.ok(listDto);
    }

    @Override
    public ResponseEntity<Void> _removerStatusConsulta(Long id) {
        removerStatusConsultaUseCase.removerStatusConsulta(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
