package com.fiap.agendamento.domain.model;

import com.fiap.agendamento.domain.enums.StatusConsultaEnum;
import com.fiap.agendamento.domain.enums.StatusNotificacaoEnum;

import java.time.OffsetDateTime;

public class AgendamentoDomain {

    private Long id;
    private String cns;
    private OffsetDateTime dataConsulta;
    private String especialidade;
    private String crmMedico;
    private String cnesLocal;
    private StatusConsultaEnum statusConsultaEnum;
    private StatusNotificacaoEnum statusNotificacaoEnum;

    public AgendamentoDomain(){
    }

    public AgendamentoDomain(Long id, String cns, OffsetDateTime dataConsulta, String especialidade, String crmMedico, String cnesLocal, StatusConsultaEnum statusConsultaEnum, StatusNotificacaoEnum statusNotificacaoEnum) {
        this.id = id;
        this.cns = cns;
        this.dataConsulta = dataConsulta;
        this.especialidade = especialidade;
        this.crmMedico = crmMedico;
        this.cnesLocal = cnesLocal;
        this.statusConsultaEnum = statusConsultaEnum;
        this.statusNotificacaoEnum = statusNotificacaoEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public OffsetDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(OffsetDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(String crmMedico) {
        this.crmMedico = crmMedico;
    }

    public String getCnesLocal() {
        return cnesLocal;
    }

    public void setCnesLocal(String cnesLocal) {
        this.cnesLocal = cnesLocal;
    }

    public StatusNotificacaoEnum getStatusNotificacaoEnum() {
        return statusNotificacaoEnum;
    }

    public void setStatusNotificacaoEnum(StatusNotificacaoEnum statusNotificacaoEnum) {
        this.statusNotificacaoEnum = statusNotificacaoEnum;
    }

    public StatusConsultaEnum getStatusConsultaEnum() {
        return statusConsultaEnum;
    }

    public void setStatusConsultaEnum(StatusConsultaEnum statusConsultaEnum) {
        this.statusConsultaEnum = statusConsultaEnum;
    }
}
