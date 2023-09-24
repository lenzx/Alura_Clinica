package med.voll.API.domain.consulta.validaciones;

import med.voll.API.domain.consulta.DatosAgendarConsulta;
import med.voll.API.infra.errores.ValidacionDeIntegracion;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas{
    public void validar(DatosAgendarConsulta datosAgendarConsulta) {
        var domingo = DayOfWeek.SUNDAY.equals(datosAgendarConsulta.fecha().getDayOfWeek());
        var antesDeApertura = datosAgendarConsulta.fecha().getHour()<7;
        var despuesDeCierre = datosAgendarConsulta.fecha().getHour()>19;
        if (domingo || antesDeApertura || despuesDeCierre) {
            throw new ValidacionDeIntegracion("El horario de atencion de la clinica es de lunes a sabado, de 07:00 a 19:00 horas");

        }
    }
}
