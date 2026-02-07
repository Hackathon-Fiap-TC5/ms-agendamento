package com.fiap.agendamento.entrypoint.controllers;

import com.fiap.agendamento.application.usecase.status.notificacao.AtualizarStatusNotificacaoUseCase;
import com.fiap.agendamento.application.usecase.status.notificacao.CriarStatusNotificacaoUseCase;
import com.fiap.agendamento.application.usecase.status.notificacao.ListarStatusNotificacaoUseCase;
import com.fiap.agendamento.application.usecase.status.notificacao.RemoverStatusNotificacaoUseCase;
import com.fiap.agendamentoDomain.StatusNotificacaoApi;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusNotificacaoRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusNotificacaoRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusNotificacaoResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class StatusNotificacaoController implements StatusNotificacaoApi {

    private final AtualizarStatusNotificacaoUseCase atualizarStatusNotificacaoUseCase;
    private final CriarStatusNotificacaoUseCase criarStatusNotificacaoUseCase;
    private final ListarStatusNotificacaoUseCase listarStatusNotificacaoUseCase;
    private final RemoverStatusNotificacaoUseCase removerStatusNotificacaoUseCase;

    public StatusNotificacaoController(AtualizarStatusNotificacaoUseCase atualizarStatusNotificacaoUseCase,
                                       CriarStatusNotificacaoUseCase criarStatusNotificacaoUseCase,
                                       ListarStatusNotificacaoUseCase listarStatusNotificacaoUseCase,
                                       RemoverStatusNotificacaoUseCase removerStatusNotificacaoUseCase) {
        this.atualizarStatusNotificacaoUseCase = atualizarStatusNotificacaoUseCase;
        this.criarStatusNotificacaoUseCase = criarStatusNotificacaoUseCase;
        this.listarStatusNotificacaoUseCase = listarStatusNotificacaoUseCase;
        this.removerStatusNotificacaoUseCase = removerStatusNotificacaoUseCase;
    }

    @Override
    public ResponseEntity<Void> _atualizarStatusNotificacao(Long id, AtualizarStatusNotificacaoRequestDto atualizarStatusNotificacaoRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> _criarStatusNotificacao(StatusNotificacaoRequestDto statusNotificacaoRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<StatusNotificacaoResponseDto>> _listarStatusNotificacao() {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> _removerStatusNotificacao(Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
