/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organizacion;

import BaseDatos.ProductosDB;
import Entidades.Alertas;
import Entidades.Producto;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ProductoFXMLController implements Initializable {
    
    ProductosDB pro = new ProductosDB();
    Producto p = null;
    
    
    boolean modificar;

    
    @FXML
    private TextField text_codigo;
    @FXML
    private TextField text_descripcion;
    @FXML
    private TextField text_tipo;
    @FXML
    private TextField text_categoria;
    @FXML
    private TextField text_linea;
    @FXML
    private TextField text_precio;
    @FXML
    private TextField text_cantidad;
    @FXML
    private TextField text_bodega;
    
    
    @FXML
    private VBox vbox_botones;
    
    @FXML
    private Button cancelar_button;
    @FXML
    private Button eliminar_button;
    @FXML
    private Button modificar_button;
    @FXML
    private Button agregar_button;
    @FXML
    private Button guardar  = new Button("Guardar");    
    
    
    
    
    public void butt_agrgOnAction(ActionEvent event) throws ClassNotFoundException, SQLException{
        
        try{
        Producto nuevo = new Producto(text_codigo.getText(), text_descripcion.getText(),text_linea.getText(),text_tipo.getText(),text_categoria.getText(), 
                            Float.parseFloat(text_precio.getText()),Integer.parseInt(text_cantidad.getText()),text_bodega.getText());
        
        //nuevo.getBodega();
        //al.setProducto(nuevo);
        
        pro.insertProducto(nuevo);
        }
        catch(Exception e){
            
            Alertas.infoBox("No se pudo Agregar!\n   1) Revisar campos introducidos", " Error al Agregar", "Alerta de Error");
            
        }
        
        
    }
    
    
    public void butt_elimOnAction(ActionEvent event) throws ClassNotFoundException, SQLException{
        
        Stage secondaryStage = new Stage();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de Confirmación");
        alert.setContentText("¿ Seguro que desea eliminar ?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            
            secondaryStage.close();
            pro.eliminarProducto(text_codigo.getText());
            butt_cancelOnAction(event);
            
        } 
        else{
            
            alert.close();
        }
        
    }
    
    public void butt_modfOnAction(ActionEvent event){
        
        guardar.setVisible(true);
        guardar.getStyleClass().add("botones");
        vbox_botones.getChildren().add(guardar);
        text_descripcion.setDisable(false);
        text_tipo.setDisable(false);
        text_linea.setDisable(false);
        text_categoria.setDisable(false);
        text_precio.setDisable(false);
        text_cantidad.setDisable(false);
        text_bodega.setDisable(false);
        
        modificar_button.setDisable(true);
        
        guardar.setOnAction(ActionEvent -> guardar(event));
        
    }
    
    private void guardar(ActionEvent event){
        
        try{
            
            Producto proModif = new Producto(text_codigo.getText(), text_descripcion.getText(),text_linea.getText(),text_tipo.getText(),text_categoria.getText(), 
                            Float.parseFloat(text_precio.getText()),Integer.parseInt(text_cantidad.getText()),text_bodega.getText());
            pro.modificarProducto(proModif);
            vbox_botones.getChildren().remove(guardar);
            butt_cancelOnAction(event);
            
        }catch(Exception e){
            
            Alertas.infoBox("No se pudo modificar!\n   1) Revisar campos introducidos", "Modificación Errónea", "Alerta de Error");            
            
        }

        butt_cancelOnAction(event);
        //System.out.println("asdsad");
    }
    
    public void butt_cancelOnAction(ActionEvent event){
        
        Stage stage = (Stage) cancelar_button.getScene().getWindow();
        stage.close();
        
    }
    
    public void butt_limpiarOnAction(ActionEvent event){
        
        text_descripcion.clear();
        text_tipo.clear();
        text_linea.clear();
        text_categoria.clear();
        text_precio.clear();
        text_cantidad.clear();
        text_bodega.clear();
        text_codigo.clear();
        
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

    public Producto getP() {
        return p;
    }

    public void initData(Producto p) {
        
        text_codigo.setDisable(true);
        text_descripcion.setDisable(true);
        text_tipo.setDisable(true);
        text_linea.setDisable(true);
        text_categoria.setDisable(true);
        text_precio.setDisable(true);
        text_cantidad.setDisable(true);
        text_bodega.setDisable(true);
        
        if(p!= null){
            text_codigo.setText(p.getCodigo());
            text_descripcion.setText(p.getDescripcion());
            text_tipo.setText(p.getTipo());
            text_linea.setText(p.getLinea());
            text_categoria.setText(p.getCategoria());
            text_precio.setText(Float.toString(p.getPrecio()));
            text_cantidad.setText(Integer.toString(p.getCantidad()));
            text_bodega.setText(p.getBodega());
            
            agregar_button.setDisable(true);
        }
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}
