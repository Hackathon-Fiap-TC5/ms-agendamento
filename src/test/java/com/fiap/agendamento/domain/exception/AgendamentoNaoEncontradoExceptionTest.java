package com.fiap.agendamento.domain.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoNaoEncontradoExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectStatus() {
        AgendamentoNaoEncontradoException exception = new AgendamentoNaoEncontradoException();

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Agendamento não encontrado.", exception.getReason());
    }
}
