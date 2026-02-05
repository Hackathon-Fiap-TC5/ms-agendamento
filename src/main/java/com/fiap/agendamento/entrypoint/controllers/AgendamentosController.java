package com.fiap.agendamento.entrypoint.controllers;

import com.fiap.agendamentoDomain.AgendamentosApi;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusConsultaRequestDto;
import com.fiap.agendamentoDomain.gen.model.ConfirmacaoAgendamentoRequestDto;
import com.fiap.agendamentoDomain.gen.model.CriarAgendamentoRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
public class AgendamentosController implements AgendamentosApi {

    @Override
    public ResponseEntity<Void> _agendamentosIdAgendamentoConfirmacaoPatch(String idAgendamento, ConfirmacaoAgendamentoRequestDto confirmacaoAgendamentoRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> _agendamentosIdAgendamentoStatusPatch(String idAgendamento, AtualizarStatusConsultaRequestDto atualizarStatusConsultaRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> _agendamentosPost(CriarAgendamentoRequestDto criarAgendamentoRequestDto) {
        return null;
    }
}
