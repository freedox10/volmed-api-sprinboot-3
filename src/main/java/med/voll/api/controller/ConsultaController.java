package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
//@SuppressWarnings("all")
public class ConsultaController {

    @Autowired
    AgendaDeConsultaService service;

    @PostMapping
    @Transactional
    //El @Operación la anotación se usa para describir una sola operación. Una operación es una combinación única de una ruta y un método HTTP.
    @Operation(
            summary = "registra una consulta",
            description = "debe existir paciente y medico o especialidad",
            tags = { "consulta", "post" })
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) throws ValidacionDeIntegridad {
        //System.out.println("datos: "+datos);

        var response = service.agendar(datos);

        return ResponseEntity.ok(response);
    }
}
