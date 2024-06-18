package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DatosCancelamientoConsulta(
        Long id,
        Long idPaciente,
        Long idMedico,
        LocalDateTime fecha,
        MotivoCancelamiento motivo
) {
    public DatosCancelamientoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getPaciente().getId(), consulta.getMedico().getId(), consulta.getData(), consulta.getMotivoCancelamiento());
    }
}
