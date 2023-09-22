package med.voll.API.controller;

import jakarta.validation.Valid;
import med.voll.API.domain.consulta.AgendaDeConsultaService;
import med.voll.API.domain.consulta.DatosAgendarConsulta;
import med.voll.API.domain.consulta.DatosDetallesConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private AgendaDeConsultaService service;
    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datosAgendarConsulta){
        System.out.println(datosAgendarConsulta);
        service.agendar(datosAgendarConsulta);
        return ResponseEntity.ok(new DatosDetallesConsulta(null,null,null,null));

    }
}
