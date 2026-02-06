package com.fiap.agendamento.domain.enums;

public enum StatusConsultaEnum {

    COMPARECEU(1, "COMPARECEU"),
    FALTOU(2, "FALTOU"),
    CANCELADA(3, "CANCELADA");

    private final int id;
    private final String status;

    StatusConsultaEnum(int id, String descricao) {
        this.id = id;
        this.status = descricao;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public static StatusConsultaEnum fromId(int id) {
        for (StatusConsultaEnum tipo : values()) {
            if (tipo.id == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Id inválido para StatusConsultaEnum: " + id);
    }

    public static StatusConsultaEnum fromStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status não pode ser nulo ou vazio");
        }

        for (StatusConsultaEnum tipo : values()) {
            if (tipo.status.equalsIgnoreCase(status)
                    || tipo.name().equalsIgnoreCase(status)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Status inválido para StatusConsultaEnum: " + status);
    }
}