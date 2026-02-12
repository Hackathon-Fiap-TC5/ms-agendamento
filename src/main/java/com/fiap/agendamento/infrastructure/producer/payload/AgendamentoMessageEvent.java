package com.fiap.agendamento.infrastructure.producer.payload;

import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

import java.time.OffsetDateTime;

public class AgendamentoMessageEvent {

    private Long idAgendamento;
    private String cns;
    private StatusConsultaDomain statusConsultaDomain;
    private StatusNotificacaoDomain statusNotificacaoDomain;
    private OffsetDateTime dataEvento;

    public AgendamentoMessageEvent(Long idAgendamento, String cns, StatusConsultaDomain statusConsultaDomain,
                                   StatusNotificacaoDomain statusNotificacaoDomain, OffsetDateTime dataEvento) {
        this.idAgendamento = idAgendamento;
        this.cns = cns;
        this.statusConsultaDomain = statusConsultaDomain;
        this.statusNotificacaoDomain = statusNotificacaoDomain;
        this.dataEvento = dataEvento;
    }

    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
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

    public OffsetDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(OffsetDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }
}
