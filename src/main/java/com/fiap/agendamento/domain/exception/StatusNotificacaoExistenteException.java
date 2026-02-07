package com.fiap.agendamento.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StatusNotificacaoExistenteException extends ResponseStatusException {

    private static final String MESSAGE = "Status Notificação já existe.";

    public StatusNotificacaoExistenteException() {
        super(HttpStatus.CONFLICT, MESSAGE);
    }
}
