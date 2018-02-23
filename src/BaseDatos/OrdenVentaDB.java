/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import Entidades.Alertas;
import Entidades.OrdenVenta;
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
public class OrdenVentaDB {
    
     private static ConexionDB conexiondb = new ConexionDB();

    public ObservableList<OrdenVenta> buscarOrden(String id){
        String buscar = null;
        if(id.equals("todos")){
           buscar = "SELECT o.idordenventa, o.fecha, o.idvendedor, o.total, c.ruc FROM ordenVenta o, cliente c where o.idcliente = c.codigo;";
        }else{
           buscar = "SELECT o.idordenventa, o.fecha, o.idvendedor, o.total, c.ruc FROM ordenVenta o, cliente c "
                   + "where o.idcliente = c.codigo and idordenventa= '" + id + "';";
        }
        
        List<OrdenVenta> lista = processListOrdenesResult(buscar);
        ObservableList<OrdenVenta> ordenes = FXCollections.observableArrayList(lista);
        return ordenes;
    }
    
    public ObservableList<OrdenVenta> buscarOrdenFecha(String codigo, String anio, String mes){
        
        String buscar = null;
        
        if(!codigo.equals("")){
            
            buscar = "SELECT o.idordenventa, o.fecha, o.idvendedor, o.total, c.ruc FROM ordenVenta o, cliente c "
                   + "where o.idcliente = c.codigo and idordenventa= '" + codigo + "';";
        
        }else{
            
            if(anio.equals("") && mes.equals(""))
                buscar = "SELECT o.idordenventa, o.fecha, o.idvendedor, o.total, c.ruc FROM ordenVenta o, cliente c where o.idcliente = c.codigo;";
                    
            else if(anio.equals(""))
                buscar  = "SELECT o.idordenventa, o.fecha, o.idvendedor, o.total, c.ruc FROM ordenVenta o, cliente c "
                   + "where o.idcliente = c.codigo and fecha like '%-" + mes + "-%';";
        
            else if(mes.equals(""))
                buscar = "SELECT o.idordenventa, o.fecha, o.idvendedor, o.total, c.ruc FROM ordenVenta o, cliente c "
                   + "where o.idcliente = c.codigo and fecha like '" + anio + "-%';";
        
            else if(!anio.equals("") && !mes.equals(""))
                buscar = "SELECT o.idordenventa, o.fecha, o.idvendedor, o.total, c.ruc FROM ordenVenta o, cliente c "
                   + "where o.idcliente = c.codigo and fecha like '" + anio + "-%' and fecha like '%-" + mes + "-%';";
            
            
        }
        
        List<OrdenVenta> lista = processListOrdenesResult(buscar);
        ObservableList<OrdenVenta> ordenes = FXCollections.observableArrayList(lista);
        
        return ordenes;
   }
    
    private List<OrdenVenta> processListOrdenesResult(String q) {
        Statement statement = null;
        ResultSet rs = null;

        List<OrdenVenta> listaOrdenes = new LinkedList<>();

        try {
            statement = conexiondb.getConexion().createStatement();
            rs = statement.executeQuery(q);
                         
            while (rs.next()) {
                OrdenVenta orden = new OrdenVenta(rs.getString("idordenventa"), rs.getString("fecha"), rs.getString("ruc"), rs.getString("idvendedor"), rs.getFloat("total"));                

                listaOrdenes.add(orden);
                //System.out.println(cliente.getNombre());
            }

            return listaOrdenes;
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
    
    
    public void insertOrdenVenta(OrdenVenta nuevo) throws ClassNotFoundException, SQLException{
        
        
        try (Connection con = conexiondb.getConexion()) {
            
            Statement stmt = con.createStatement();
            
            

            String insert= "INSERT INTO ordenVenta (idordenventa, fecha, idcliente, idvendedor,total) VALUES "
                + "('"+ nuevo.getIdorden() + "','" + nuevo.getFecha() + "', '" + nuevo.getIdCliente() + "', '" + nuevo.getIdEmpleado() + 
                    "'," + nuevo.getTotal() + ");";
            
            stmt.executeUpdate(insert);
            Alertas.infoBox("Orden Realizada", "Exitoso", "Aviso"); 
            
            System.out.print("agregó");
        }
        catch(Exception e){
            
        }
    }
    
//    public void eliminarDepartamento(OrdenVenta depa){
//        
//        ConexionDB c = new ConexionDB();
//        try{
//            Connection con = c.getConexion();
//            Statement st = con.createStatement();
//            String eliminar = "DELETE from departamento where codigo='" + depa.getCodigo() + "';";
//            st.executeUpdate(eliminar);
//            System.out.print("eliminó");
//        }
//        catch(Exception e){
//            
//        }
//        
//    }
    
    public int cantidadOrdenes(){
        
        ConexionDB c = new ConexionDB();
        ResultSet rs = null;
        Statement stmt = null;
        int cantidad = -1;
        try{
            Connection con = c.getConexion();
            stmt = con.createStatement();

            
            String q = "SELECT count(*) as count FROM ordenVenta;";
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
