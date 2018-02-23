/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import Entidades.Alertas;
import Entidades.Departamento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class DepartamentosDB {
    
    private static ConexionDB conexiondb = new ConexionDB();

    public ObservableList<Departamento> buscarDepartamento(String personas){
        String buscar = null;
        if(personas.equals("todos")){
           buscar = "SELECT * from departamentos;";
        }else{
           buscar = "SELECT * from departamentos where nombre like '%" + personas + "%';" ;
        }
//        
        List<Departamento> lista = processListDepartamentosResult(buscar);
        ObservableList<Departamento> departamentos = FXCollections.observableArrayList(lista);
        return departamentos;
   }
    
    private List<Departamento> processListDepartamentosResult(String q) {
        Statement statement = null;
        ResultSet rs = null;

        List<Departamento> listaDepartamentos = new LinkedList<>();

        try {
            statement = conexiondb.getConexion().createStatement();
            rs = statement.executeQuery(q);
                         
            while (rs.next()) {
                Departamento departamento = new Departamento(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("nomina"));                


                listaDepartamentos.add(departamento);
                //System.out.println(cliente.getNombre());
            }

            return listaDepartamentos;
        } 
        
        catch (ClassNotFoundException | SQLException ex) {
                    Stage secondaryStage = new Stage();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Diálogo de Confirmación");
                    alert.setContentText("");
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
    
    
    public void insertDepartamento(Departamento nuevo) throws ClassNotFoundException, SQLException{
        
        ConexionDB c = new ConexionDB();
        
        try (Connection con = c.getConexion()) {
            
            Statement stmt = con.createStatement();

            String insert= "INSERT INTO departamentos (codigo, nombre, nomina) VALUES "
                + "("+ nuevo.getCodigo() + ",'" + nuevo.getNombre() + "', '" + nuevo.getNomina() + "');";
            
            stmt.executeUpdate(insert);
            
            System.out.print("agregó");
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo Agregar!\n   1) Revisar campos introducidos", " Error al Agregar", "Alerta de Error");
            
        }
    }
    
    public void eliminarDepartamento(Departamento depa){
        
        ConexionDB c = new ConexionDB();
        try{
            Connection con = c.getConexion();
            Statement st = con.createStatement();
            String eliminar = "DELETE from departamento where codigo='" + depa.getCodigo() + "';";
            st.executeUpdate(eliminar);
            System.out.print("eliminó");
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo eliminar!\n   1) Revisar depedencia en Ordenes de Venta o \n   2) Campos introducidos", "Eliminación Errónea", "Alerta de Error");
            
        }
        
    }
}
