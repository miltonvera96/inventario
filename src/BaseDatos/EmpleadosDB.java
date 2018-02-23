/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;
import Entidades.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class EmpleadosDB {
    
    private static ConexionDB conexiondb = new ConexionDB();
    
    public static Empleado validarEmpleado(String user, String password){
        //String query = "select * from empleado where usuario = '" + user + "' and password='" + password + ";";
          String query = "SELECT * FROM Empleados WHERE usuario ='" + user + "';";
        Statement stm = null;
        ResultSet rs = null;
        Empleado item = null;
        
        try{
            
            stm = conexiondb.getConexion().createStatement();
            rs = stm.executeQuery(query);
            
            if(rs.next()){
                
                item = new Empleado(rs.getString("idEmpleado"), rs.getString("nombre"), rs.getString("apellido"),rs.getString("direccion"),
                        rs.getString("estado_Activo"),rs.getInt("administrador"), rs.getFloat("sueldo"), rs.getString("cargo") ,rs.getString("usuario"), rs.getString("clave")
                );          
                
            }
            return item;
        }
        catch(Exception ex){
            
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }

            conexiondb.CloseConnection();
        }
        return null;
    }
    
   public ObservableList<Empleado> buscarEmpleado(String personas){
        String buscar = null;
        if(personas.equals("todos")){
           buscar = "SELECT * from empleados;";
        }else{
           buscar = "SELECT * from Empleados where nombre like '%" + personas + "%';" ;
        }
//        
        List<Empleado> lista = processListEmpleadosResult(buscar);
        ObservableList<Empleado> empleados = FXCollections.observableArrayList(lista);
        return empleados;
   }
    
    private List<Empleado> processListEmpleadosResult(String q) {
        Statement statement = null;
        ResultSet rs = null;

        List<Empleado> listaEmpleados = new LinkedList<>();

        try {
            statement = conexiondb.getConexion().createStatement();
            rs = statement.executeQuery(q);

            while (rs.next()) {
                Empleado empleado = new Empleado(rs.getString("idEmpleado"), rs.getString("nombre"), rs.getString("apellido"),rs.getString("direccion"),
                        rs.getString("estado_Activo"),rs.getInt("administrador"), rs.getFloat("sueldo"), rs.getString("cargo") ,rs.getString("usuario"), rs.getString("clave")
                        );                


                listaEmpleados.add(empleado);
            }

            return listaEmpleados;
        } 
        
        catch (ClassNotFoundException | SQLException ex) {
                    Stage secondaryStage = new Stage();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Diálogo de Confirmación");
                    alert.setContentText("Esta seguro que desea ordenar esto?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        secondaryStage.close();
                        //addSalida(pane);
                    } else{
                        alert.close();
                    }
            //System.out.print("Error, process Result Cliente: " + ex.getMessage());
            return null;
        } 
        
        finally {
            
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }

            conexiondb.CloseConnection();
        }
    }
    
    
    
    
}
