package com.clinica.agendamento.controller;

import com.clinica.agendamento.dto.AgendamentoRequest;
import com.clinica.agendamento.dto.AgendamentoResponse;
import com.clinica.agendamento.dto.PacienteResponse;
import com.clinica.agendamento.model.Agendamento;
import com.clinica.agendamento.model.Paciente;
import com.clinica.agendamento.repository.PacienteRepository;
import com.clinica.agendamento.service.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;
    private final PacienteRepository pacienteRepository;

    public AgendamentoController(AgendamentoService service, PacienteRepository pacienteRepository) {
        this.service = service;
        this.pacienteRepository = pacienteRepository;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponse> criar(@RequestBody AgendamentoRequest request) {
        Paciente paciente = pacienteRepository.findById(request.pacienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));

        Agendamento agendamento = Agendamento.builder()
                .dataHora(request.dataHora())
                .especialidade(request.especialidade())
                .paciente(paciente)
                .build();

        Agendamento novo = service.criarAgendamento(agendamento);

        return ResponseEntity.ok(new AgendamentoResponse(
                novo.getId(),
                novo.getDataHora(),
                novo.getEspecialidade(),
                new PacienteResponse(
                        paciente.getId(),
                        paciente.getNome(),
                        paciente.getEmail(),
                        paciente.getTelefone()
                )
        ));
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponse>> listar() {
        List<AgendamentoResponse> agendamentos = service.listarAgendamentos().stream()
                .map(a -> new AgendamentoResponse(
                        a.getId(),
                        a.getDataHora(),
                        a.getEspecialidade(),
                        new PacienteResponse(
                                a.getPaciente().getId(),
                                a.getPaciente().getNome(),
                                a.getPaciente().getEmail(),
                                a.getPaciente().getTelefone()
                        )
                ))
                .toList();

        return ResponseEntity.ok(agendamentos);
    }
}
