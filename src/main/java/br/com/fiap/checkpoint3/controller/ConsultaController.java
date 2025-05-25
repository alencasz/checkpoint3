package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.StatusConsulta;
import br.com.fiap.checkpoint3.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    public ResponseEntity<Consulta> create(@RequestBody Consulta consulta) {
        consulta.setStatusConsulta(StatusConsulta.AGENDADA); // Default
        Consulta saved = repository.save(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> getAll(
        @RequestParam(required = false) StatusConsulta status,
        @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataDe,
        @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataAte
    ) {
        LocalDateTime start = dataDe != null ? dataDe.atStartOfDay() : null;
        LocalDateTime end = dataAte != null ? dataAte.atTime(23, 59, 59) : null;

        List<Consulta> consultas = repository.findByStatusConsultaAndDataConsultaBetween(status, start, end);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> getById(@PathVariable Long id) {
        return repository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> update(@PathVariable Long id, @RequestBody Consulta consulta) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        consulta.setId(id);
        Consulta updated = repository.save(consulta);
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