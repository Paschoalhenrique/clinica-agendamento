package com.clinica.agendamento.repository;

import com.clinica.agendamento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByDataHora(LocalDateTime dataHora);
}

