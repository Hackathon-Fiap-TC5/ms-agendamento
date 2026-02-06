package com.fiap.agendamento.infrastructure.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_status_notificacao")
public class StatusNotificacaoEntity {

    @Id
    private Long id;
    private String status;
}
