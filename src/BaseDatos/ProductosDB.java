/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import Entidades.Alertas;
import Entidades.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class ProductosDB {
    
    private static ConexionDB conexiondb = new ConexionDB();

    public ObservableList<Producto> buscarProducto(String nombre){
        String buscar = null;
        if(nombre.equals("todos")){
           buscar = "SELECT * from producto;";
        }else{
           buscar = "SELECT * from producto where descripcion like '%" + nombre + "%';" ;
        }
//        
        List<Producto> lista = processListProductosResult(buscar);
        ObservableList<Producto> productos = FXCollections.observableArrayList(lista);
        return productos;
   }
    
    
    public ObservableList<Producto> buscarPorBodega(String bodega){
        
           String buscar = "SELECT * from productos where bodega='" + bodega + "';" ;
        
//        
        List<Producto> lista = processListProductosResult(buscar);
        ObservableList<Producto> productos = FXCollections.observableArrayList(lista);
        return productos;
   }
    
    private List<Producto> processListProductosResult(String q) {
        Statement statement = null;
        ResultSet rs = null;

        List<Producto> listaProductos = new LinkedList<>();

        try {
            statement = conexiondb.getConexion().createStatement();
            rs = statement.executeQuery(q);
                         
            while (rs.next()) {
                Producto producto = new Producto(rs.getString("codigo"), rs.getString("descripcion"), rs.getString("tipo"),rs.getString("linea"),rs.getString("categoria"),
                        rs.getFloat("precio"),rs.getInt("cantidad"),rs.getString("bodega"));                


                listaProductos.add(producto);
                //System.out.println(cliente.getNombre());
            }

            return listaProductos;
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
    
    
    public void insertProducto(Producto nuevo) throws ClassNotFoundException, SQLException{
        
        ConexionDB c = new ConexionDB();
        
        try{
            Connection con = c.getConexion();
            Statement stmt = con.createStatement();

            String insert= "INSERT INTO productos (codigo, descripcion, tipo, linea, categoria, precio, bodega, cantidad) VALUES"
                + "('"+ nuevo.getCodigo() + "','" + nuevo.getDescripcion() + "', '" + nuevo.getTipo() +  "','" + nuevo.getLinea() + "', '" + nuevo.getCategoria() +
                     "'," + nuevo.getPrecio() + ", '" + nuevo.getBodega() +  "'," + nuevo.getCantidad() + ");";
            
            stmt.executeUpdate(insert);
            
            System.out.print("agregó");
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo Agregar!\n   1) Revisar campos introducidos", " Error al Agregar", "Alerta de Error");
            
        }
    }
    
    public void eliminarProducto(String  codigo) {
        
        try {
            ConexionDB c = new ConexionDB();
            
            Connection con = c.getConexion();
            Statement st = con.createStatement();
            String eliminar = "DELETE from productos where codigo='" + codigo + "';";
            st.executeUpdate(eliminar);
            System.out.print("eliminó");
        } catch (Exception e) {
            Alertas.infoBox("No se pudo eliminar!\n   1) Revisar depedencia en Ordenes de Venta o \n   2) Campos introducidos", "Eliminación Errónea", "Alerta de Error");

        }
        
        
    }
    
    public void modificarProducto(Producto modificar){
        
        ConexionDB c = new ConexionDB();
        try{
            Connection con = c.getConexion();
            Statement stmt = con.createStatement();

            
            String insert = "UPDATE productos SET" +
                            "`descripcion`='" + modificar.getDescripcion() + "'," +
                            "`tipo`='" + modificar.getTipo() + "'," +
                            "`linea`='" + modificar.getLinea() + "'," +
                            "`categoria`='" + modificar.getCategoria() + "'," +
                            " `precio`=" + modificar.getPrecio() + "," +
                            " `cantidad`=" + modificar.getCantidad() + "," +
                            " `bodega`='" + modificar.getBodega() + "'" +
                            " WHERE `codigo`='" + modificar.getCodigo() + "'; ";
            
            stmt.executeUpdate(insert);
            
            System.out.print("modificó");
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo modificar!\n   1) Revisar campos introducidos", "Modificación Errónea", "Alerta de Error");
            
            System.out.print("no modificó");
        }
        
    }
    
}
