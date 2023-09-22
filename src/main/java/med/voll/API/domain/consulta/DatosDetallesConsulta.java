package med.voll.API.domain.consulta;

import java.time.LocalDateTime;

public record DatosDetallesConsulta(Long id, Long idPaciente, Long idMedico, LocalDateTime fecha) {
}
