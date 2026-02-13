package com.fiap.agendamento.entrypoint.controllers;

import com.fiap.agendamento.application.usecase.filaviva.ConsultarFilaVivaUseCase;
import com.fiap.agendamento.application.usecase.filaviva.PublicarFilaVivaUseCase;
import com.fiap.agendamento.domain.model.FilaVivaDomain;
import com.fiap.agendamento.entrypoint.controllers.presenter.FilaVivaPresenter;
import com.fiap.agendamentoDomain.FilaVivaApi;
import com.fiap.agendamentoDomain.gen.model.FilaVivaResponseDto;
import com.fiap.agendamentoDomain.gen.model.PublicarFilaVivaRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class FilaVivaController implements FilaVivaApi {

    private final ConsultarFilaVivaUseCase consultarFilaVivaUseCase;
    private final PublicarFilaVivaUseCase publicarFilaVivaUseCase;

    public FilaVivaController(ConsultarFilaVivaUseCase consultarFilaVivaUseCase,
                              PublicarFilaVivaUseCase publicarFilaVivaUseCase) {
        this.consultarFilaVivaUseCase = consultarFilaVivaUseCase;
        this.publicarFilaVivaUseCase = publicarFilaVivaUseCase;
    }

    @Override
    public ResponseEntity<List<FilaVivaResponseDto>> _consultarFilaViva() {
        List<FilaVivaDomain> domains = consultarFilaVivaUseCase.consultarFilaViva();
        List<FilaVivaResponseDto> dtos = FilaVivaPresenter.toListFilaVivaResponseDto(domains);
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<Void> _publicarFilaViva(PublicarFilaVivaRequestDto publicarFilaVivaRequestDto) {
        FilaVivaDomain domain = FilaVivaPresenter.toFilaVivaDomain(publicarFilaVivaRequestDto);
        publicarFilaVivaUseCase.publicarFilaViva(domain);
        return ResponseEntity.accepted().build();
    }
}
