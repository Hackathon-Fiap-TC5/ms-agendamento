package com.fiap.agendamento.domain.model;

import java.time.OffsetDateTime;

public class AgendamentoDomain {

    private Long id;
    private String cns;
    private OffsetDateTime dataConsulta;
    private String especialidade;
    private String crmMedico;
    private String cnesLocal;
    private StatusConsultaDomain statusConsultaDomain;
    private StatusNotificacaoDomain statusNotificacaoDomain;

    public AgendamentoDomain(){
    }

    public AgendamentoDomain(Long id, String cns, OffsetDateTime dataConsulta, String especialidade, String crmMedico, String cnesLocal, StatusConsultaDomain statusConsultaDomain, StatusNotificacaoDomain statusNotificacaoDomain) {
        this.id = id;
        this.cns = cns;
        this.dataConsulta = dataConsulta;
        this.especialidade = especialidade;
        this.crmMedico = crmMedico;
        this.cnesLocal = cnesLocal;
        this.statusConsultaDomain = statusConsultaDomain;
        this.statusNotificacaoDomain = statusNotificacaoDomain;
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
