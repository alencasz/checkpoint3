package br.com.fiap.checkpoint3.repository;

import br.com.fiap.checkpoint3.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p ORDER BY p.nome ASC")
    List<Paciente> findAllOrderByNomeAsc();

    @Query("SELECT p FROM Paciente p ORDER BY p.nome DESC")
    List<Paciente> findAllOrderByNomeDesc();
}