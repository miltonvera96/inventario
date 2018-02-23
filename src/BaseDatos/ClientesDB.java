/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import Entidades.Alertas;
import Entidades.Cliente;
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
public class ClientesDB {
    
    private static ConexionDB conexiondb = new ConexionDB();
    
    
   public ObservableList<Cliente> buscarCliente(String personas){
        String buscar = null;
        if(personas.equals("todos")){
           buscar = "SELECT * from cliente;";
        }else{
           buscar = "SELECT * from cliente where nombre like '%" + personas + "%';" ;
        }
//        
        List<Cliente> lista = processListClientesResult(buscar);
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(lista);
        return clientes;
   }
    
    private List<Cliente> processListClientesResult(String q) {
        Statement statement = null;
        ResultSet rs = null;

        List<Cliente> listaClientes = new LinkedList<>();

        try {
            statement = conexiondb.getConexion().createStatement();
            rs = statement.executeQuery(q);
                         
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("codigo"), rs.getString("ruc"), rs.getString("nombre"), rs.getString("telefono"),rs.getString("ciudad"),
                        rs.getString("direccion"),rs.getString("tipo"));                


                listaClientes.add(cliente);
                //System.out.println(cliente.getNombre());
            }

            return listaClientes;
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
    
    
    public boolean insertCliente(Cliente nuevo) throws ClassNotFoundException, SQLException{
        
        ConexionDB c = new ConexionDB();
        
        try (Connection con = c.getConexion()) {
            
            Statement stmt = con.createStatement();

            String insert= "INSERT INTO cliente (codigo, ruc, nombre, tipo, ciudad, direccion, telefono) VALUES "
                + "('" + nuevo.getCodigo() + "', '" + nuevo.getRuc() + "', '" + nuevo.getNombre() + "','" + nuevo.getTipo() + "', '" + nuevo.getCiudad() + "','"
                + nuevo.getDireccion() + "', '"+ nuevo.getTelefono() + "');";
            
            stmt.executeUpdate(insert);
            
            System.out.print("agregó");
            return true;
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo Agregar Cliente!\n   1) Revisar campos introducidos", " Error al Agregar", "Alerta de Error");
            return false;
        }
    }
    
    public boolean eliminarCliente(String codigo){
        
        ConexionDB c = new ConexionDB();
        try{
            Connection con = c.getConexion();
            Statement st = con.createStatement();
            String eliminar = "DELETE from cliente where codigo='" + codigo + "';";
            st.executeUpdate(eliminar);
            System.out.print("eliminó");
            conexiondb.CloseConnection();
            return true;
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo eliminar!\n   1) Revisar depedencia en Ordenes de Venta o \n   2) Campos introducidos", "Eliminación Errónea", "Alerta de Error");
            return false;
        }
    }
    
    public void modificarCliente(Cliente modificar){
        
        ConexionDB c = new ConexionDB();
        try{
            Connection con = c.getConexion();
            Statement stmt = con.createStatement();

            
            String insert = "UPDATE cliente SET" +
                            "`ruc`='" + modificar.getRuc() + "'," +
                            "`tipo`='" + modificar.getTipo() + "'," +
                            "`nombre`='" + modificar.getNombre() + "'," +
                            "`ciudad`='" + modificar.getCiudad() + "'," +
                            " `direccion`='" + modificar.getDireccion() + "'," +
                            " `telefono`='" + modificar.getTelefono() + "'" +
                            " WHERE `codigo`='" + modificar.getCodigo() + "'; ";
            
            stmt.executeUpdate(insert);
            
            System.out.print("modificó");
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo modificar!\n   1) Revisar campos introducidos", "Modificación Errónea", "Alerta de Error");

            System.out.print("no modificó");
        }
        conexiondb.CloseConnection();
        
    }
    
    public int cantidadClientes(){
        
        ConexionDB c = new ConexionDB();
        ResultSet rs = null;
        Statement stmt = null;
        int cantidad = -1;
        try{
            Connection con = c.getConexion();
            stmt = con.createStatement();

            
            String q = "SELECT count(*) as count FROM Cliente;";
            rs = stmt.executeQuery(q);
            while(rs.next()){
                cantidad = rs.getInt("count");
            }
            System.out.print(cantidad);
        }
        catch(Exception e){
            System.out.print("no contó");
        }
        finally {
            
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }

            conexiondb.CloseConnection();
        }
        
        return cantidad;
    }
    
}
