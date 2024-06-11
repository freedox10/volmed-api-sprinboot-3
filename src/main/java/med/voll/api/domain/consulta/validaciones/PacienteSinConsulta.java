package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.DatosAgendarConsulta;

public class PacienteSinConsulta {
    public void validar(DatosAgendarConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
    }

}
