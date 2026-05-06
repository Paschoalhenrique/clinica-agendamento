package com.clinica.agendamento.dto;

public record PacienteRequest (
        String nome,
        String email,
        String telefone
) {}
