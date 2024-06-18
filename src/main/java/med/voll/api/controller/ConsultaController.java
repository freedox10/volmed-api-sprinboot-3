package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
//@SuppressWarnings("all")
public class ConsultaController {

    @Autowired
    AgendaDeConsultaService service;

    @Autowired
    ConsultaRepository consultaRepository;

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

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity cancelarConsulta(@PathVariable Long id, @RequestBody @Valid DatosCancelamientoConsulta datos) {
        //Consulta consulta = consultaRepository.getReferenceById(id);
        //Consulta consulta = new Consulta();
        DatosCancelamientoConsulta cancelamientoConsulta = new DatosCancelamientoConsulta(
                id, datos.idPaciente(), datos.idMedico(), datos.fecha(), datos.motivo());
        System.out.println("cancelamientoConsulta: "+cancelamientoConsulta);
        service.cancelar(cancelamientoConsulta);

        return ResponseEntity.noContent().build();
    }

}
