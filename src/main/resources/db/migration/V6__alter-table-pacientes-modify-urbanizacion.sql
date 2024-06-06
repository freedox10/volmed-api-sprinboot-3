alter table pacientes change urbanizacion calle varchar(100) not null;
alter table pacientes drop codigoPostal;
alter table pacientes drop provincia;