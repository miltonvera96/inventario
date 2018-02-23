/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import Entidades.Bodega;
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
public class BodegaDB {
    
    private static ConexionDB conexiondb = new ConexionDB();

    public ObservableList<Bodega> buscarBodega(String nombre){
        String buscar = null;
        if(nombre.equals("todos")){
           buscar = "SELECT * from bodega;";
        }else{
           buscar = "SELECT * from bodega where descripcion like '%" + nombre + "%';" ;
        }
//        
        List<Bodega> lista = processListBodegasResult(buscar);
        ObservableList<Bodega> bodegas = FXCollections.observableArrayList(lista);
        return bodegas;
   }
    
    private List<Bodega> processListBodegasResult(String q) {
        Statement statement = null;
        ResultSet rs = null;

        List<Bodega> listaBodegas = new LinkedList<>();

        try {
            statement = conexiondb.getConexion().createStatement();
            rs = statement.executeQuery(q);
                         
            while (rs.next()) {
                Bodega bodega = new Bodega(rs.getString("idbodega"), rs.getString("tipo"), rs.getString("direccion"));

                listaBodegas.add(bodega);
                //System.out.println(cliente.getNombre());
            }

            return listaBodegas;
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
    
}
