package com.fiap.agendamento.entrypoint.controllers.presenter;

import com.fiap.agendamento.domain.model.FilaVivaDomain;
import com.fiap.agendamento.entrypoint.controllers.mappers.FilaVivaDtoMapper;
import com.fiap.agendamentoDomain.gen.model.FilaVivaResponseDto;
import com.fiap.agendamentoDomain.gen.model.PublicarFilaVivaRequestDto;

import java.util.List;

public class FilaVivaPresenter {

    public static FilaVivaDomain toFilaVivaDomain(PublicarFilaVivaRequestDto publicarFilaVivaRequestDto) {
        return FilaVivaDtoMapper.INSTANCE.toFilaVivaDomain(publicarFilaVivaRequestDto);
    }

    public static List<FilaVivaResponseDto> toListFilaVivaResponseDto(List<FilaVivaDomain> domains) {
        return FilaVivaDtoMapper.INSTANCE.toListFilaVivaResponseDto(domains);
    }
}
