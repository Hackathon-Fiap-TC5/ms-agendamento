package com.fiap.agendamento.infrastructure.database.implementations;

import com.fiap.agendamento.application.gateway.FilaVivaGateway;
import com.fiap.agendamento.domain.model.FilaVivaDomain;
import com.fiap.agendamento.infrastructure.database.entities.FilaVivaEntity;
import com.fiap.agendamento.infrastructure.database.mappers.FilaVivaEntityMapper;
import com.fiap.agendamento.infrastructure.database.repositories.FilaVivaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilaVivaGatewayImpl implements FilaVivaGateway {

    private final FilaVivaRepository filaVivaRepository;

    @Override
    public void salvar(FilaVivaDomain domain) {
        FilaVivaEntity entity = FilaVivaEntityMapper.INSTANCE.toFilaVivaEntity(domain);
        filaVivaRepository.save(entity);
    }

    @Override
    public List<FilaVivaDomain> consultarFilaViva() {
        return filaVivaRepository.findAll()
                .stream().map(
                        FilaVivaEntityMapper.INSTANCE::toFilaVivaDomain
                ).collect(Collectors.toList());
    }
}
