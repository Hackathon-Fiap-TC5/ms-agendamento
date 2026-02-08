package com.fiap.agendamento.domain.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class StatusNotificacaoNaoEncontradoExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectStatus() {
        StatusNotificacaoNaoEncontradoException exception = new StatusNotificacaoNaoEncontradoException();

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Status Notificação não encontrado.", exception.getReason());
    }
}
