package com.isil.mantenimiento;

import javax.swing.*;
import java.lang.reflect.Type;
import java.sql.*;

public class MainApp {
    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/mantenimiento",
                "root",
                "root"
        );

        //Insertamos datos a la tabla Tb_Repuesto con el objeto Statement

        String query= " insert into Tb_Repuesto   values('RP006', 'Filtro de combustible', 'Orginal','DENSO', 50)";
        Statement stm=cnx.createStatement();
        int filasInsertada =stm.executeUpdate(query);
        System.out.println("Fila insertada: " + filasInsertada);

        //Realizamos la verificacion del registro insertado
        ResultSet rs = stm.executeQuery("select * from Tb_Repuesto where idRep='RP006'");

        while (rs.next()) {
            System.out.println(rs.getString("idRep"));
            System.out.println(rs.getString("Nombre_Rep"));
            System.out.println(rs.getString("Cantidad_Rep") + "\n");

        }

        //---------------------------------------------------------------------------------

        //Actualizamos los datos de la tabla Tb_Repuesto con el objeto PreparedStatement

        PreparedStatement preparedStmt = cnx.prepareStatement("UPDATE Tb_Repuesto SET Marca_Rep=?, Cantidad_Rep=?  WHERE idRep=?");
        preparedStmt.setString(1,"VALEO");
        preparedStmt.setInt(2,80);
        preparedStmt.setString(3,"RP005");
        int filaActualizada=preparedStmt.executeUpdate();
        System.out.println("Fila Actualizada: " + filaActualizada);

        PreparedStatement actualizar=cnx.prepareStatement("select * from Tb_Repuesto where idRep=?");
        actualizar.setString(1,"RP005");

        //Realizamos la verificacion del registro actualizado
        ResultSet datoActualizado =actualizar.executeQuery();

        while (datoActualizado.next()) {
            System.out.println( datoActualizado.getString("idRep"));
            System.out.println( datoActualizado.getString("Marca_Rep"));
            System.out.println( datoActualizado.getString("Cantidad_Rep") + "\n");
        }

        //---------------------------------------------------------------------------------

        //Eliminamos un registro  de la tabla Tb_Repuesto con el objeto CallableStatement

        CallableStatement callSp=cnx.prepareCall("{call usp_EliminarRepuesto(?,?)}");
        callSp.setString(1,"RP001");
        callSp.registerOutParameter(2, Types.INTEGER);

        ResultSet fila= callSp.executeQuery();
         if(fila==null){
             System.out.println("Niguna Fila eliminada");
         }else{
             System.out.println("Fila Eliminada: 1");
         }

        int result = callSp.getInt(2);

        if(result == 0) {
            System.out.println("Registro Eliminado Correctamente");
        }


        cnx.close();
    }
}
