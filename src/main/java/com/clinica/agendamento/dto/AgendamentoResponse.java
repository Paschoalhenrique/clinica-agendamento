package com.clinica.agendamento.dto;

import java.time.LocalDateTime;

public record AgendamentoResponse (
        Long id,
        LocalDateTime dataHora,
        String especialidade,
        PacienteResponse paciente
) {}
