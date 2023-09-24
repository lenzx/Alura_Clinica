package med.voll.API.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.API.domain.consulta.ConsultaRepository;
import med.voll.API.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas{
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosAgendarConsulta datosAgendarConsulta){
        if(datosAgendarConsulta.idMedico() == null){
            return;
        }
        var medicoConConsulta = consultaRepository.existsByMedicoIdAndData(datosAgendarConsulta.idMedico(), datosAgendarConsulta.fecha());

        if (medicoConConsulta){
            throw new ValidationException("este medico ya tiene una consulta en ese horario");
        }
    }
}
