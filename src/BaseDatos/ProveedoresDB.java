/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import Entidades.Alertas;
import Entidades.Proveedor;
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
public class ProveedoresDB {
    
    private static ConexionDB conexiondb = new ConexionDB();
    
    
   public ObservableList<Proveedor> buscarProveedor(String personas){
        String buscar = null;
        if(personas.equals("todos")){
           buscar = "SELECT * from proveedores;";
        }else{
           buscar = "SELECT * from proveedores where descripcion like '%" + personas + "%';" ;
        }
//        
        List<Proveedor> lista = processListProveedoresResult(buscar);
        ObservableList<Proveedor> proveedores = FXCollections.observableArrayList(lista);
        return proveedores;
   }
    
    private List<Proveedor> processListProveedoresResult(String q) {
        Statement statement = null;
        ResultSet rs = null;

        List<Proveedor> listaProveedores = new LinkedList<>();

        try {
            statement = conexiondb.getConexion().createStatement();
            rs = statement.executeQuery(q);
                         
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(rs.getString("ruc"), rs.getString("descripcion"), rs.getString("ciudad"),rs.getString("estado"),
                        rs.getString("direccion"));                


                listaProveedores.add(proveedor);
                //System.out.println(cliente.getNombre());
            }

            return listaProveedores;
        } 
        
        catch (ClassNotFoundException | SQLException ex) {
                    Stage secondaryStage = new Stage();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Diálogo de Confirmación");
                    alert.setContentText("No se encontró");
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
    
    
    public void insertProveedor(Proveedor nuevo) throws ClassNotFoundException, SQLException{
        
        ConexionDB c = new ConexionDB();
        
        try (Connection con = c.getConexion()) {
            
            Statement stmt = con.createStatement();

            String insert= "INSERT INTO proveedores (ruc, descripcion, direccion, estado, ciudad) VALUES "
                + "('" + nuevo.getRuc() + "', '" + nuevo.getDescripcion() + "','" + nuevo.getDireccion() + "', '" + nuevo.getEstado() + "','"
                + nuevo.getCiudad()  + "');";
            
            stmt.executeUpdate(insert);
            
            Stage secondaryStage = new Stage();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Diálogo de Confirmación");
            alert.setContentText("Nuevo Proveedor Agregado!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                secondaryStage.close();
                //addSalida(pane);
            }else{
                alert.close();
            }
            
            //System.out.print("agregó");
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo Agregar!\n   1) Revisar campos introducidos", " Error al Agregar", "Alerta de Error");
            
        }
    }
    
    public void eliminarProveedor(Proveedor proveedor){
        
        ConexionDB c = new ConexionDB();
        try{
            Connection con = c.getConexion();
            Statement st = con.createStatement();
            String eliminar = "DELETE from proveedores where ruc='" + proveedor.getRuc() + "';";
            st.executeUpdate(eliminar);
            System.out.print("eliminó");
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo eliminar!\n   1) Revisar depedencia en Ordenes de Venta o \n   2) Campos introducidos", "Eliminación Errónea", "Alerta de Error");

        }
        
    }
    
    
    public void modificarProveedor(Proveedor modificar){
        
        ConexionDB c = new ConexionDB();
        try{
            Connection con = c.getConexion();
            Statement stmt = con.createStatement();

            
            String insert = "UPDATE proveedores SET" +
                            "`descripcion`='" + modificar.getDescripcion() + "'," +
                            "`direccion`='" + modificar.getDireccion() + "'," +
                            "`estado`='" + modificar.getEstado() + "'," +
                            "`ciudad`='" + modificar.getCiudad() + "'" +
                            " WHERE `ruc`='" + modificar.getRuc() + "'; ";
            
            stmt.executeUpdate(insert);
            
            System.out.print("modificó");
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo modificar!\n   1) Revisar campos introducidos", "Modificación Errónea", "Alerta de Error");
            
            System.out.print("no modificó");
        }
        
    }
    
}
