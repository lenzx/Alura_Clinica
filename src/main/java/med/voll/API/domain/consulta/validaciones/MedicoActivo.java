package med.voll.API.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.API.domain.consulta.DatosAgendarConsulta;
import med.voll.API.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidadorDeConsultas{
    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validar(DatosAgendarConsulta datosAgendarConsulta) {
        if (datosAgendarConsulta.idMedico()== null){
            return;
        }
        var medicoActivo = medicoRepository.findActivoById(datosAgendarConsulta.idMedico());
        if (!medicoActivo){
            throw new ValidationException("no se permitir agendar citas con medicos inactivos en el sistema");
        }
    }
}
