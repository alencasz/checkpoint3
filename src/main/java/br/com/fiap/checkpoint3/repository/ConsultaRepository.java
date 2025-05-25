package br.com.fiap.checkpoint3.repository;

import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.StatusConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByStatusConsultaAndDataConsultaBetween(
        StatusConsulta status,
        LocalDateTime dataDe,
        LocalDateTime dataAte
    );

    List<Consulta> findByProfissionalIdAndStatusConsultaAndDataConsultaBetween(
        Long profissionalId,
        StatusConsulta status,
        LocalDateTime dataDe,
        LocalDateTime dataAte
    );

    List<Consulta> findByPacienteIdAndStatusConsultaAndDataConsultaBetween(
        Long pacienteId,
        StatusConsulta status,
        LocalDateTime dataDe,
        LocalDateTime dataAte
    );
}