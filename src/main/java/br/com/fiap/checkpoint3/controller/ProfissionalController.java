package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.model.Profissional;
import br.com.fiap.checkpoint3.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {
    @Autowired
    private ProfissionalRepository repository;

    @PostMapping
    public ResponseEntity<Profissional> create(@RequestBody Profissional profissional) {
        Profissional saved = repository.save(profissional);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> getAll(@RequestParam String sort) {
        List<Profissional> profissionais = sort.equals("asc")
            ? repository.findAllOrderByNomeAsc()
            : repository.findAllOrderByNomeDesc();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> getById(@PathVariable Long id) {
        return repository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/stats")
public ResponseEntity<Long> getStats(@PathVariable Long id) {
    Long count = repository.countConsultasByProfissionalId(id); // Método corrigido
    return ResponseEntity.ok(count);
}

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> update(@PathVariable Long id, @RequestBody Profissional profissional) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        profissional.setId(id);
        Profissional updated = repository.save(profissional);
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