package com.clinica.agendamento.controller;

import com.clinica.agendamento.dto.PacienteRequest;
import com.clinica.agendamento.dto.PacienteResponse;
import com.clinica.agendamento.model.Paciente;
import com.clinica.agendamento.repository.PacienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository repository;

    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> criar(@RequestBody PacienteRequest request) {
        Paciente paciente = Paciente.builder()
                .nome(request.nome())
                .email(request.email())
                .telefone(request.telefone())
                .build();

        Paciente salvo = repository.save(paciente);

        return ResponseEntity.ok(new PacienteResponse(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getTelefone()
        ));
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listar() {
        List<PacienteResponse> pacientes = repository.findAll().stream()
                .map(p -> new PacienteResponse(p.getId(), p.getNome(), p.getEmail(), p.getTelefone()))
                .toList();

        return ResponseEntity.ok(pacientes);
    }
}
