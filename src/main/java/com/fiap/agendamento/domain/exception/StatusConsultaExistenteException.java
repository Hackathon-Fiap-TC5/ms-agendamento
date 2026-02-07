package com.fiap.agendamento.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StatusConsultaExistenteException extends ResponseStatusException {

    private static final String MESSAGE = "Status Consulta já existe.";

    public StatusConsultaExistenteException() {
        super(HttpStatus.CONFLICT, MESSAGE);
    }
}
