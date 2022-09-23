
/*creamos la tabla*/

create table Tb_Repuesto(
idRep varchar(5) not null primary key,
Nombre_Rep varchar(30),
Tipo_Rep varchar (20),
Marca_Rep varchar (20),
Cantidad_Rep int
)

/*insertamos datos*/

INSERT INTO Tb_Repuesto(idRep, Nombre_Rep, Tipo_Rep, Marca_Rep, Cantidad_Rep )
VALUES
('RP001', 'Pastillas de freno', 'Orginal','RIDEX', 51),
('RP002', 'Discos de freno', 'Alternativo', 'VALEO', 63),
('RP003', 'Latiguillos de freno', 'Orginal', 'BREMBO', 35),
('RP004', 'Pinzas de freno', 'Alternativo','DELPHI', 16),
('RP005', 'Cable de freno de mano', 'Alternativo', 'RIDEX', 24)



/*---------------------------------------------------------------------------------------------------------------*/
/*creamos el store procedure para actualizar un repuesto */
/*no se uso para ninguna operacion requerida en la evaluacion */
create procedure usp_ActualizarRepuesto
(codigo varchar(5),
Nombre varchar(30),
tipo varchar(20),
marca varchar(20),
cantidad int)
begin
	update Tb_Repuesto set  idRep=codigo, Nombre_Rep=Nombre, Tipo_Rep=tipo,Marca_Rep=marca, Cantidad_Rep=cantidad
	where idRep=codigo;
end

/*verificamos el store procedure que permite actualizar un repuesto*/
call usp_ActualizarRepuesto('RP001', 'Pastillas de freno', 'Orginal','RIDEX', 60);
/*---------------------------------------------------------------------------------------------------------------*/


/*creamos el store procedure para eliminar un repuesto*/
create  procedure usp_EliminarRepuesto(in  codigoRep varchar(5), out resultado int)
begin
	delete from Tb_Repuesto
	where idRep=codigoRep;
end

/*verificamos el store procedure que permite eliminar un repuesto*/
call  usp_EliminarRepuesto('RP006',@outResult);

select *from tb_repuesto