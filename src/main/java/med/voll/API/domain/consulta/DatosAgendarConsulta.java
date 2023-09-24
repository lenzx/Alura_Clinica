package med.voll.API.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.API.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(Long id, @NotNull Long idPaciente, Long idMedico, @NotNull @Future LocalDateTime fecha, Especialidad especialidad) {
    public DatosAgendarConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getPaciente().getId(), consulta.getMedico().getId(), consulta.getData(), consulta.getMedico().getEspecialidad() );
    }
}
