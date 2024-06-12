package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datos){
        if(datos.idMedico() == null){
            return;
        }

        var medicoConsulta = consultaRepository.existsByMedicoIdAndData(datos.idMedico(),datos.fecha());
        if (medicoConsulta){
            throw new ValidationException("Este medico ya tiene una consulta en ese horario");
        }
    }

}
