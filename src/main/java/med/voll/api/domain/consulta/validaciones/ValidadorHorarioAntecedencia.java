package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamiento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamientoDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosCancelamientoConsulta datos){
        var consulta = consultaRepository.getReferenceById(datos.id());
        var ahora = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(ahora, consulta.getData()).toHours();

        if (diferenciaEnHoras < 24){
            throw new ValidationException("Consulta solo puede ser cancelada con antecedencia minima de 24 horas");
        }
    }
}
