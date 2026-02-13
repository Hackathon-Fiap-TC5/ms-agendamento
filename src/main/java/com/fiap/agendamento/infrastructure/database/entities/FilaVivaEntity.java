package com.fiap.agendamento.infrastructure.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "tb_fila_viva")
public class FilaVivaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilaViva;

    private Long idAgendamento;
    private String cns;
    private String sugestaoConduta;
    private String perfilPaciente;
    private String justificativa;
    private OffsetDateTime dataEntrada;
}
