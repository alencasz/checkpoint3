package br.com.fiap.checkpoint3.repository;

import br.com.fiap.checkpoint3.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    @Query("SELECT p FROM Profissional p ORDER BY p.nome ASC")
    List<Profissional> findAllOrderByNomeAsc();

    @Query("SELECT p FROM Profissional p ORDER BY p.nome DESC")
    List<Profissional> findAllOrderByNomeDesc();

    // Consulta corrigida para contar consultas por profissional
    @Query("SELECT COUNT(c) FROM Consulta c WHERE c.profissional.id = :profissionalId")
    Long countConsultasByProfissionalId(@Param("profissionalId") Long profissionalId);
}