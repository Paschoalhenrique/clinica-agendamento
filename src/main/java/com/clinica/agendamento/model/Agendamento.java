package com.clinica.agendamento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agendamento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull(message = "Data e hora são obrigatórias")
        @Future(message = "Agendamento deve ser em uma data futura")
        private LocalDateTime dataHora;

        @NotBlank(message = "Especialidade é obrigatória")
        private String especialidade;

        @ManyToOne
        @JoinColumn(name = "paciente_id", nullable = false)
        private Paciente paciente;
}
