package med.voll.API.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.API.domain.consulta.ConsultaRepository;
import med.voll.API.domain.consulta.DatosAgendarConsulta;
import med.voll.API.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PacienteSinConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){
        var primerHorario = datosAgendarConsulta.fecha().withHour(7);
        var ultimoHorario= datosAgendarConsulta.fecha().withHour(18);

        var pacienteConsulta = consultaRepository.existByPacienteIdAndDataBetween(datosAgendarConsulta.idPaciente(),primerHorario ,ultimoHorario);

        if (pacienteConsulta){
            throw new ValidationException(" ");
        }
    }
}
