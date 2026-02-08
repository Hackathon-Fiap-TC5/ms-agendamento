package com.fiap.agendamento.entrypoint.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HealthControllerTest {

    private HealthController healthController;

    @BeforeEach
    void setUp() {
        healthController = new HealthController();
    }

    @Test
    void shouldReturnHealthStatus() {
        ResponseEntity<Map<String, String>> response = healthController.health();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("UP", response.getBody().get("status"));
        assertEquals("ms-agendamento", response.getBody().get("service"));
    }

    @Test
    void shouldReturnHealthStatusOnRoot() {
        ResponseEntity<Map<String, String>> response = healthController.root();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("UP", response.getBody().get("status"));
        assertEquals("ms-agendamento", response.getBody().get("service"));
    }
}
