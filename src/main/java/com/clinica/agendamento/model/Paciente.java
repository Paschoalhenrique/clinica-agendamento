package com.clinica.agendamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Paciente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Nome é obrigatório")
        private String nome;

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        private String email;

        @NotBlank(message = "Telefone é obrigatório")
        private String telefone;
}

