package com.fiap.agendamento.infrastructure.database.entities;

import com.fiap.agendamento.domain.enums.StatusNotificacaoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "tb_agendamento")
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cns;

    @Column(name = "data_consulta")
    private OffsetDateTime dataConsulta;

    private String especialidade;

    @Column(name = "crm_medico")
    private String crmMedico;

    @Column(name = "cnes_local")
    private String cnesLocal;

    @ManyToOne
    @JoinColumn(name = "tb_status_notificacao_id")
    private StatusNotificacaoEntity statusNotificacao;

    @ManyToOne
    @JoinColumn(name = "tb_status_consulta_id")
    private StatusConsultaEntity statusConsulta;
}
