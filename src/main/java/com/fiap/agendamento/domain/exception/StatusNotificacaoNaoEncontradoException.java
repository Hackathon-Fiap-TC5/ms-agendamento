package com.fiap.agendamento.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StatusNotificacaoNaoEncontradoException extends ResponseStatusException {

    private static final String MESSAGE = "Status Notificação não encontrado.";

    public StatusNotificacaoNaoEncontradoException() {
        super(HttpStatus.NOT_FOUND, MESSAGE);
    }
}
