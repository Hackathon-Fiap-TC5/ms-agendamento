package com.fiap.agendamento.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StatusConsultaNaoEncontradoException extends ResponseStatusException {

    private static final String MESSAGE = "Status não encontrado.";

    public StatusConsultaNaoEncontradoException() {
        super(HttpStatus.NOT_FOUND, MESSAGE);
    }
}
