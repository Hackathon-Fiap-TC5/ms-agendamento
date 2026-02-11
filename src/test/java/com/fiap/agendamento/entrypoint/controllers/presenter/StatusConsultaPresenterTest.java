package com.fiap.agendamento.entrypoint.controllers.presenter;

import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusConsultaRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusConsultaRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusConsultaResponseDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatusConsultaPresenterTest {

    @Test
    void shouldConvertToStatusConsultaDomain() {
        StatusConsultaRequestDto requestDto = new StatusConsultaRequestDto();
        requestDto.setStatus("COMPARECEU");

        StatusConsultaDomain result = StatusConsultaPresenter.toStatusConsultaDomain(requestDto);

        assertNotNull(result);
        assertEquals("COMPARECEU", result.getStatus());
    }

    @Test
    void shouldConvertToListStatusConsultaResponseDto() {
        List<StatusConsultaDomain> domains = Arrays.asList(
                new StatusConsultaDomain(1L, "COMPARECEU"),
                new StatusConsultaDomain(2L, "FALTOU")
        );

        List<StatusConsultaResponseDto> result = StatusConsultaPresenter.toListStatusConsultaResponseDto(domains);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("COMPARECEU", result.get(0).getStatus());
        assertEquals(2L, result.get(1).getId());
        assertEquals("FALTOU", result.get(1).getStatus());
    }

    @Test
    void shouldConvertEmptyListToStatusConsultaResponseDto() {
        List<StatusConsultaResponseDto> result = StatusConsultaPresenter.toListStatusConsultaResponseDto(Collections.emptyList());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldConvertToStatusConsultaDomainAtualizar() {
        AtualizarStatusConsultaRequestDto requestDto = new AtualizarStatusConsultaRequestDto();
        requestDto.setStatus("FALTOU");

        StatusConsultaDomain result = StatusConsultaPresenter.toStatusConsultaDomainAtualizar(requestDto);

        assertNotNull(result);
        assertEquals("FALTOU", result.getStatus());
    }
}
