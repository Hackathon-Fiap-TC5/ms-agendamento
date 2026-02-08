package com.fiap.agendamento.domain.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class StatusConsultaExistenteExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectStatus() {
        StatusConsultaExistenteException exception = new StatusConsultaExistenteException();

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        assertEquals("Status Consulta já existe.", exception.getReason());
    }
}
