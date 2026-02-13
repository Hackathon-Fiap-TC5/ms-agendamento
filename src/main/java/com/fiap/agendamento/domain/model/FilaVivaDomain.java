package com.fiap.agendamento.domain.model;

import java.time.OffsetDateTime;

public class FilaVivaDomain {

    private Long idAgendamento;
    private String cns;
    private String sugestaoConduta;
    private String perfilPaciente;
    private String justificativa;
    private Long idFilaViva;
    private OffsetDateTime dataEntrada;

    public FilaVivaDomain() {
    }

    public FilaVivaDomain(Long idAgendamento, String cns, String sugestaoConduta, String perfilPaciente, String justificativa, Long idFilaViva, OffsetDateTime dataEntrada) {
        this.idAgendamento = idAgendamento;
        this.cns = cns;
        this.sugestaoConduta = sugestaoConduta;
        this.perfilPaciente = perfilPaciente;
        this.justificativa = justificativa;
        this.idFilaViva = idFilaViva;
        this.dataEntrada = dataEntrada;
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

    public String getSugestaoConduta() {
        return sugestaoConduta;
    }

    public void setSugestaoConduta(String sugestaoConduta) {
        this.sugestaoConduta = sugestaoConduta;
    }

    public String getPerfilPaciente() {
        return perfilPaciente;
    }

    public void setPerfilPaciente(String perfilPaciente) {
        this.perfilPaciente = perfilPaciente;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Long getIdFilaViva() {
        return idFilaViva;
    }

    public void setIdFilaViva(Long idFilaViva) {
        this.idFilaViva = idFilaViva;
    }

    public OffsetDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(OffsetDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
}
