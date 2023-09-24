package med.voll.API.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.API.domain.consulta.ConsultaRepository;
import med.voll.API.domain.consulta.DatosAgendarConsulta;
import med.voll.API.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements  ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){
        var primerHorario = datosAgendarConsulta.fecha().withHour(7);
        var ultimoHorario= datosAgendarConsulta.fecha().withHour(18);

        var pacienteConsulta = consultaRepository.existsByPacienteIdAndDataBetween(datosAgendarConsulta.idPaciente(),primerHorario ,ultimoHorario);

        if (pacienteConsulta){
            throw new ValidationException("El paciente ya tiene una consulta para ese dia");
        }
    }
}
