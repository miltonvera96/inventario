/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import Entidades.DetalleVenta;
import Entidades.Producto;
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
public class DetalleVentaDB {
    
    private static ConexionDB conexiondb = new ConexionDB();

     public ObservableList<DetalleVenta> buscarPorNOrden(String orden){
        
           String buscar = "SELECT d.idventa, d.idproducto, p.descripcion, d.cantidad, d.preciounitario, d.idorden " +
                            "FROM DetalleVenta d, productos p " +
                            "WHERE d.idproducto = p.codigo " +
                            "AND d.idorden ='"+ orden +"';";
        
         //System.out.println("Aqui llegue");
        List<DetalleVenta> lista = processListDVentasResult(buscar);
        ObservableList<DetalleVenta> dventas = FXCollections.observableArrayList(lista);
        return dventas;
    }
    
     private List<DetalleVenta> processListDVentasResult(String q) {
        Statement statement = null;
        ResultSet rs = null;

        List<DetalleVenta> listaDVentas = new LinkedList<>();

        try {
            statement = conexiondb.getConexion().createStatement();
            rs = statement.executeQuery(q);
            
            System.out.println("Aqui llegue");
            while (rs.next()) {
                DetalleVenta dventa = new DetalleVenta(rs.getString("idventa"),rs.getString("idproducto"),rs.getString("descripcion"),
                        rs.getFloat("preciounitario"),rs.getInt("cantidad"),rs.getString("idorden"));                

                System.out.println(dventa.getDescripcion());
                listaDVentas.add(dventa);
                //System.out.println(cliente.getNombre());
            }

            return listaDVentas;
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
     
    public void insertList(ObservableList<DetalleVenta> ventas) throws ClassNotFoundException, SQLException{
        
        for(DetalleVenta d : ventas){

                insertdetalleVenta(d);
                System.out.println("Cantidad del producto" + d.getCantidad());
                
            
        }
    }
    
    private void insertdetalleVenta(DetalleVenta nuevo) throws ClassNotFoundException, SQLException{
        
        
//        try (Connection con = conexiondb.getConexion()) {
            Connection con = conexiondb.getConexion();
            Statement stmt = con.createStatement();

            String insert= "INSERT INTO DetalleVenta (idVenta, idproducto, cantidad, preciounitario, idorden) VALUES "
                + "('"+ nuevo.getIddetalle() + "','" + nuevo.getIdproducto() + "', " + nuevo.getCantidad() + 
                    "," + nuevo.getPrecio() + ", '" + nuevo.getIdorden() +"');";
            
            stmt.executeUpdate(insert);
            
            System.out.print("agregó");
//        }
//        catch(Exception e){
//            
//        }
        conexiondb.CloseConnection();
    }
    
    
    public int cantidadDetalles(){
        
        ConexionDB c = new ConexionDB();
        ResultSet rs = null;
        Statement stmt = null;
        int cantidad = -1;
        try{
            Connection con = c.getConexion();
            stmt = con.createStatement();

            
            String q = "SELECT count(*) as count FROM DetalleVenta;";
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
