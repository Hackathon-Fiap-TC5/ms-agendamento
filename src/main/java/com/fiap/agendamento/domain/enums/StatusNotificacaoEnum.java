package com.fiap.agendamento.domain.enums;

public enum StatusNotificacaoEnum {

    CONFIRMOU_48H_ANTECEDENCIA(1, "CONFIRMOU_48H_ANTECEDENCIA"),
    CONFIRMOU_24H_ANTECEDENCIA(2, "CONFIRMOU_24H_ANTECEDENCIA"),
    NAO_CONFIRMOU(3, "NAO_CONFIRMOU"),
    SEM_RETORNO(4, "SEM_RETORNO");

    private final int id;
    private final String status;

    StatusNotificacaoEnum(int id, String descricao) {
        this.id = id;
        this.status = descricao;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public static StatusNotificacaoEnum fromId(int id) {
        for (StatusNotificacaoEnum tipo : values()) {
            if (tipo.id == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Id inválido para StatusNotificacao: " + id);
    }

    public static StatusNotificacaoEnum fromStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status não pode ser nulo ou vazio");
        }

        for (StatusNotificacaoEnum tipo : values()) {
            if (tipo.status.equalsIgnoreCase(status)
                    || tipo.name().equalsIgnoreCase(status)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Status inválido para StatusNotificacao: " + status);
    }
}