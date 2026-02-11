package com.fiap.agendamento.entrypoint.controllers.presenter;

import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusNotificacaoRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusNotificacaoRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusNotificacaoResponseDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatusNotificacaoPresenterTest {

    @Test
    void shouldConvertToStatusNotificacaoDomain() {
        StatusNotificacaoRequestDto requestDto = new StatusNotificacaoRequestDto();
        requestDto.setStatus("CONFIRMOU_48H_ANTECEDENCIA");

        StatusNotificacaoDomain result = StatusNotificacaoPresenter.toStatusNotificacaoDomain(requestDto);

        assertNotNull(result);
        assertEquals("CONFIRMOU_48H_ANTECEDENCIA", result.getStatus());
    }

    @Test
    void shouldConvertToListStatusNotificacaoResponseDto() {
        List<StatusNotificacaoDomain> domains = Arrays.asList(
                new StatusNotificacaoDomain(1L, "CONFIRMOU_48H_ANTECEDENCIA"),
                new StatusNotificacaoDomain(2L, "CONFIRMOU_24H_ANTECEDENCIA")
        );

        List<StatusNotificacaoResponseDto> result = StatusNotificacaoPresenter.toListStatusNotificacaoResponseDto(domains);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("CONFIRMOU_48H_ANTECEDENCIA", result.get(0).getStatus());
        assertEquals(2L, result.get(1).getId());
        assertEquals("CONFIRMOU_24H_ANTECEDENCIA", result.get(1).getStatus());
    }

    @Test
    void shouldConvertEmptyListToStatusNotificacaoResponseDto() {
        List<StatusNotificacaoResponseDto> result = StatusNotificacaoPresenter.toListStatusNotificacaoResponseDto(Collections.emptyList());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldConvertToStatusNotificacaoDomainAtualizar() {
        AtualizarStatusNotificacaoRequestDto requestDto = new AtualizarStatusNotificacaoRequestDto();
        requestDto.setStatus("CONFIRMOU_24H_ANTECEDENCIA");

        StatusNotificacaoDomain result = StatusNotificacaoPresenter.toStatusNotificacaoDomainAtualizar(requestDto);

        assertNotNull(result);
        assertEquals("CONFIRMOU_24H_ANTECEDENCIA", result.getStatus());
    }
}
