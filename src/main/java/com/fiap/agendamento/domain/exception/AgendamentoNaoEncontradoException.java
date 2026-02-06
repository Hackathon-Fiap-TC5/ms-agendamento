package com.fiap.agendamento.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AgendamentoNaoEncontradoException extends ResponseStatusException {

    private static final String MESSAGE = "Agendamento não encontrado.";

    public AgendamentoNaoEncontradoException() {
        super(HttpStatus.NOT_FOUND, MESSAGE);
    }
}
