package com.clinica.agendamento.dto;

import java.time.LocalDateTime;

public record AgendamentoRequest (
        LocalDateTime dataHora,
        String especialidade,
        Long pacienteId
) {}