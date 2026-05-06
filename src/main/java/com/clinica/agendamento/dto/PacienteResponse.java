package com.clinica.agendamento.dto;

public record PacienteResponse (
        Long id,
        String nome,
        String email,
        String telefone
) {}
