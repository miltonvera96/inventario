/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import Entidades.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class QueryEmpleados {

    
    public QueryEmpleados(){
        

    }
    public void insertEmpleado(Empleado nuevo) throws ClassNotFoundException, SQLException{
        
        ConexionDB c = new ConexionDB();
        
        try (Connection con = c.getConexion()) {
            Statement stmt = con.createStatement();

//String query = "INSERT INTO Empleados (idEmpleado, nombre, apellido, direccion, estado_Activo, sueldo, cargo, administrador, usuario, clave) VALUES ('1234', 'sdf', 'sdfsdf', 'sdfsf', 0, 400, 'sdfsf', 1, 'sfsdf', 'sdf');";

            String insert= "INSERT INTO empleados (idEmpleado, nombre, apellido, direccion, estado_Activo, sueldo, cargo, administrador, usuario, clave) VALUES "
                + "('" + nuevo.getIdEmpleado() + "', '" + nuevo.getNombre() + "','" + nuevo.getApellido() + "', '" + nuevo.getDireccion() + "', "
                + nuevo.getEstado_Activo()+ ", " + Float.toString(nuevo.getSueldo()) + ", '"+ nuevo.getCargo() + "', " + Integer.toString(nuevo.getAdministrador()) + ", '" + nuevo.getUsuario() + "', '" + nuevo.getPassword() + "');";
            stmt.executeUpdate(insert);
            System.out.print("agregó");
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo Agregar!\n   1) Revisar campos introducidos", " Error al Agregar", "Alerta de Error");

        }
    }
    
    public boolean eliminarEmpleado(Empleado emp){
        
        ConexionDB c = new ConexionDB();
        try{
            Connection con = c.getConexion();
            Statement st = con.createStatement();
            String eliminar = "DELETE from empleados where idEmpleado='" + emp.getIdEmpleado() + "';";
            st.executeUpdate(eliminar);
            System.out.print("eliminó");
            return true;
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo eliminar!\n   1) Revisar depedencia en Ordenes de Venta o \n   2) Campos introducidos", "Eliminación Errónea", "Alerta de Error");
            return false;
        }
        
    }
    
    public void modificarEmpleado(Empleado modificar){
        
        ConexionDB c = new ConexionDB();
        try{
            Connection con = c.getConexion();
            Statement stmt = con.createStatement();

            
            String insert = "UPDATE empleados SET" +
                            "`nombre`='" + modificar.getNombre() + "'," +
                            "`apellido`='" + modificar.getApellido() + "'," +
                            "`direccion`='" + modificar.getDireccion() + "'," +
                            "`estado_Activo`='" + modificar.getEstado_Activo() + "'," +
                            " `sueldo`=" + modificar.getSueldo() + "," +
                            " `administrador`=" + modificar.getAdministrador() + "," +
                            " `usuario`='" + modificar.getUsuario() + "'," +
                            " `clave`='" + modificar.getPassword() + "'," +
                            " `cargo`='" + modificar.getCargo() + "'" +
                            " WHERE `idEmpleado`=" + modificar.getIdEmpleado() + "; ";
            
            stmt.executeUpdate(insert);
            
            System.out.print("modificó");
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo modificar!\n   1) Revisar campos introducidos", "Modificación Errónea", "Alerta de Error");            
            System.out.print("no modificó");
        }
        
    }
    
}
