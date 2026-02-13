package com.fiap.agendamento.entrypoint.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.fiap.agendamento.application.usecase.filaviva.ConsultarFilaVivaUseCase;
import com.fiap.agendamento.application.usecase.filaviva.PublicarFilaVivaUseCase;
import com.fiap.agendamento.domain.model.FilaVivaDomain;
import com.fiap.agendamentoDomain.gen.model.FilaVivaResponseDto;
import com.fiap.agendamentoDomain.gen.model.PublicarFilaVivaRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class FilaVivaControllerTest {

    @Mock
    private ConsultarFilaVivaUseCase consultarFilaVivaUseCase;

    @Mock
    private PublicarFilaVivaUseCase publicarFilaVivaUseCase;

    @InjectMocks
    private FilaVivaController filaVivaController;

    @Test
    void deveConsultarFilaVivaComSucesso() {
        FilaVivaDomain domain = new FilaVivaDomain();
        domain.setIdAgendamento(1L);
        domain.setCns("123456789012345");

        when(consultarFilaVivaUseCase.consultarFilaViva())
                .thenReturn(List.of(domain));

        ResponseEntity<List<FilaVivaResponseDto>> response =
                filaVivaController._consultarFilaViva();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());

        verify(consultarFilaVivaUseCase, times(1))
                .consultarFilaViva();
    }

    @Test
    void devePublicarFilaVivaERetornarAccepted() {
        PublicarFilaVivaRequestDto request = new PublicarFilaVivaRequestDto();
        request.setIdAgendamento(10L);
        request.setCns("987654321000000");
        request.setSugestaoConduta("CONFIAVEL");
        request.setJustificativa("Cancelamento recente");

        doNothing().when(publicarFilaVivaUseCase).publicarFilaViva(any(FilaVivaDomain.class));

        ResponseEntity<Void> response =
                filaVivaController._publicarFilaViva(request);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertNull(response.getBody());

        verify(publicarFilaVivaUseCase, times(1)).publicarFilaViva(any(FilaVivaDomain.class));
    }
}