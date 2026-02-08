package com.fiap.agendamento.infrastructure.queue.payload;

import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

public class AgendamentoMessageEvent {

    private String cns;
    private StatusConsultaDomain statusConsultaDomain;
    private StatusNotificacaoDomain statusNotificacaoDomain;

    public AgendamentoMessageEvent(String cns,
                                   StatusConsultaDomain statusConsultaDomain,
                                   StatusNotificacaoDomain statusNotificacaoDomain) {
        this.cns = cns;
        this.statusConsultaDomain = statusConsultaDomain;
        this.statusNotificacaoDomain = statusNotificacaoDomain;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public StatusConsultaDomain getStatusConsultaDomain() {
        return statusConsultaDomain;
    }

    public void setStatusConsultaDomain(StatusConsultaDomain statusConsultaDomain) {
        this.statusConsultaDomain = statusConsultaDomain;
    }

    public StatusNotificacaoDomain getStatusNotificacaoDomain() {
        return statusNotificacaoDomain;
    }

    public void setStatusNotificacaoDomain(StatusNotificacaoDomain statusNotificacaoDomain) {
        this.statusNotificacaoDomain = statusNotificacaoDomain;
    }
}
