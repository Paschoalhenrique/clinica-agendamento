package com.clinica.agendamento.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agendamento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDateTime dataHora;

        private String especialidade;

        @ManyToOne
        @JoinColumn(name = "paciente_id", nullable = false)
        private Paciente paciente;
}
