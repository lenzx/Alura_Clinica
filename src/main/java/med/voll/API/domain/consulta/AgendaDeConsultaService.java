package med.voll.API.domain.consulta;

import med.voll.API.controller.PacienteController;
import med.voll.API.domain.consulta.validaciones.HorarioDeAnticipacion;
import med.voll.API.domain.consulta.validaciones.ValidadorDeConsultas;
import med.voll.API.domain.medico.Medico;
import med.voll.API.domain.medico.MedicoRepository;
import med.voll.API.domain.paciente.Paciente;
import med.voll.API.domain.paciente.PacienteRepository;
import med.voll.API.infra.errores.ValidacionDeIntegracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    List<ValidadorDeConsultas> validadores;

    public DatosDetallesConsulta agendar(DatosAgendarConsulta datosAgendarConsulta){

        if(!pacienteRepository.findById(datosAgendarConsulta.idPaciente()).isPresent()){
            throw new ValidacionDeIntegracion("id de paciene no encontrado");
        }
        if (datosAgendarConsulta.idMedico() != null && !medicoRepository.existsById(datosAgendarConsulta.idMedico())){
            throw  new ValidacionDeIntegracion("id de medico no encontrado");
        }

        validadores.forEach(v->v.validar(datosAgendarConsulta));

        var paciente = pacienteRepository.findById(datosAgendarConsulta.idPaciente()).get();
        var medico = seleccionarMedico(datosAgendarConsulta);

        if (medico == null){
            throw  new ValidacionDeIntegracion("no existen medicos disponibles para este horario y especialidad");
        }

        var consulta = new Consulta(null, medico, paciente, datosAgendarConsulta.fecha());
        consultaRepository.save(consulta);

        return new DatosDetallesConsulta(consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datosAgendarConsulta) {
        if (datosAgendarConsulta.idMedico() != null){
            return medicoRepository.getReferenceById(datosAgendarConsulta.idMedico());
        }
        if(datosAgendarConsulta.especialidad() == null){
            throw new ValidacionDeIntegracion("debe seleccionar una especialidad para el medico");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datosAgendarConsulta.especialidad(), datosAgendarConsulta.fecha());
    }
}
