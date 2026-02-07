package com.fiap.agendamento.infrastructure.database.implementations;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.infrastructure.database.repositories.StatusNotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusNotificacaoGatewayImpl implements StatusNotificacaoGateway {

    private final StatusNotificacaoRepository statusNotificacaoRepository;


}
