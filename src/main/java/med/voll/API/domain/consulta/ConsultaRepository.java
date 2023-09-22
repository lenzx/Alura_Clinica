package med.voll.API.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
}