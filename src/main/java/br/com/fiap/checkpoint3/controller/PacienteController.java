package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.model.Paciente;
import br.com.fiap.checkpoint3.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
        Paciente saved = repository.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll(@RequestParam String sort) {
        List<Paciente> pacientes = sort.equals("asc") 
            ? repository.findAllOrderByNomeAsc() 
            : repository.findAllOrderByNomeDesc();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable Long id) {
        return repository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente paciente) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        paciente.setId(id);
        Paciente updated = repository.save(paciente);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}