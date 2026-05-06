package com.clinica.agendamento.service;

import com.clinica.agendamento.model.Agendamento;
import com.clinica.agendamento.repository.AgendamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Agendamento criarAgendamento(Agendamento agendamento) {
        validarData(agendamento.getDataHora());
        verificarDisponibilidade(agendamento.getDataHora());

        return repository.save(agendamento);
    }

    public List<Agendamento> listarAgendamentos() {
        return repository.findAll();
    }

    private void validarData(LocalDateTime dataHora) {
        LocalDateTime agora = LocalDateTime.now();
        if (dataHora.isBefore(agora.plusDays(1))) {
            throw new IllegalArgumentException("Agendamento deve ser feito com pelo menos 1 dia de antecedência.");
        }
    }

    private void verificarDisponibilidade(LocalDateTime dataHora) {
        if (repository.existsByDataHora(dataHora)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Horário já ocupado, escolha outro.");
        }
    }
}
