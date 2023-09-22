package med.voll.API.domain.consulta.validaciones;

import med.voll.API.domain.consulta.DatosAgendarConsulta;
import med.voll.API.infra.errores.ValidacionDeIntegracion;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

public class HorarioDeAnticipacion {
    public void validar(DatosAgendarConsulta datosAgendarConsulta){
        var ahora = LocalDateTime.now();
        var horaConsulta = datosAgendarConsulta.fecha();
        var diferenciaDe30min = Duration.between(ahora,horaConsulta).toMinutes() < 30;

        if(diferenciaDe30min){
            throw new ValidacionDeIntegracion("La consulta debe programarse con almenos 30 minutos de anticipacion");
        }
    }
}
