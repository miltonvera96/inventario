/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class InicioFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML private Label infecha;
    @FXML private Label inhora;
    
   @FXML
   public void empleadosOnAction(ActionEvent e) throws IOException{
       
        Parent loader = FXMLLoader.load(getClass().getResource("/Mantenimiento/EmpleadosFXML.fxml"));
        
        Stage stage = new Stage();
        stage.setTitle("Empleados");
        stage.setScene(new Scene(loader,918,720));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show(); 
    
    }
   
   
    @FXML
    public void salirOnAction(){
       
       Platform.exit();
    }
   
   
    @FXML
    public void proveedoresOnAction(ActionEvent event){
       
       try{
            Parent loader = FXMLLoader.load(getClass().getResource("/Mantenimiento/ProveedoresFXML.fxml"));
        
            Stage stage = new Stage();
            stage.setTitle("Proveedores");
            stage.setScene(new Scene(loader,800,700));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show(); 
       }
       catch(Exception e ){
           System.out.print("no se pudo");
       }
       
    }
    
    @FXML
    public void departamentosOnAction(ActionEvent event) throws IOException{
       
        try{
        Parent loader = FXMLLoader.load(getClass().getResource("/Organizacion/DepartamentosFXML.fxml"));
        
        Stage stage = new Stage();
        stage.setTitle("Departamentos");
        stage.setScene(new Scene(loader,921,725));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        }catch(IOException e){
            System.out.println("No se pudo mostrar Departamento");
        }
        
    }
    
    @FXML
   public void almacenOnAction(){
       
       try{
        Parent loader = FXMLLoader.load(getClass().getResource("/Organizacion/AlmacenFXML.fxml"));
        
        Stage stage = new Stage();
        stage.setTitle("Almacen");
        stage.setScene(new Scene(loader,1200,600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        }catch(IOException e){
            System.out.println("No se pudo mostrar almacen");
        }
       
    }
    
    
   
//    public void inicioVentana() throws IOException{
//        
//        //FXMLLoader fxmlLoader = new FXMLLoader(SeleccionarProductoController.class.getResource("SeleccionarProducto.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/Mantenimiento/ClientesFXML.fxml"));  
//        //Parent root = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setTitle("Seleccionar Producto");
//            stage.setScene(new Scene(root));
//            stage.initModality(Modality.WINDOW_MODAL);
//            //stage.initOwner(parent);
//
//            //InicioFXMLController controller = fxmlLoader.getController();
//            stage.showAndWait();
//    }
    
    
    public void clientesOnAction(ActionEvent e) throws IOException{
        
        Parent loader = FXMLLoader.load(getClass().getResource("/Mantenimiento/ClientesFXML.fxml"));
        
        Stage stage = new Stage();
        stage.setTitle("Clientes");
        stage.setScene(new Scene(loader,921,725));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show(); 
    }
    
    
    @FXML
    public void crearOrdenOnAction(ActionEvent event) throws IOException{
       
       try{
            Parent loader = FXMLLoader.load(getClass().getResource("/ordenes/OrdenesFXML.fxml"));
        
            Stage stage = new Stage();
            stage.setTitle("Facturas");
            stage.setScene(new Scene(loader,1000,800));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show(); 
       }
       catch(Exception e ){
           e.printStackTrace();
           System.out.print("no se pudo");
       }
       
    }
    
    public void verOrdenOnAction(ActionEvent event) throws IOException{
       
       //try{
            Parent loader = FXMLLoader.load(getClass().getResource("/ordenes/BuscaOFXML.fxml"));
        
            Stage stage = new Stage();
            stage.setTitle("Ventas");
            stage.setScene(new Scene(loader,1400,600));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show(); 
       //}
       //catch(Exception e ){
           //System.out.print("no se pudo");
       //}
       
    }
    
    
    public void reportsOnAction(ActionEvent event) throws IOException{
        
        Parent loader = FXMLLoader.load(getClass().getResource("/Reportes/ReportesFXML.fxml"));
        
            Stage stage = new Stage();
            stage.setTitle("Ventas");
            stage.setScene(new Scene(loader,500,400));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show(); 
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Fecha f = new Fecha();
        
        infecha.setText(f.getFechaNumero());
        inhora.setText(f.getHora());
    }    
    
}
