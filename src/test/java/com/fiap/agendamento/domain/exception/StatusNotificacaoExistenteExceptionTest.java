package com.fiap.agendamento.domain.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class StatusNotificacaoExistenteExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectStatus() {
        StatusNotificacaoExistenteException exception = new StatusNotificacaoExistenteException();

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        assertEquals("Status Notificação já existe.", exception.getReason());
    }
}
