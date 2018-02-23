/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import BaseDatos.ProveedoresDB;
import Entidades.Proveedor;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ProveedoresFXMLController implements Initializable {
    
    ProveedoresDB p = new ProveedoresDB();
    ObservableList<Proveedor> proveedores;
    
    //------//
    //-TABLA//
    //------//
    
    @FXML
    private TableView tablaProveedores;
    
    @FXML
    private TableColumn<Proveedor, String> tabcolp_ruc;
    @FXML
    private TableColumn<Proveedor, String> tabcolp_descripcion;
    @FXML
    private TableColumn<Proveedor, String> tabcolp_direccion;
    @FXML
    private TableColumn<Proveedor, String> tabcolp_ciudad;
    @FXML
    private TableColumn<Proveedor, String> tabcolp_estado;
    
    
    
    @FXML
    private TextField buscar_proveedor;
    @FXML
    private Button boton_bucar;
    @FXML
    private Button boton_terminar;
    
    
    //-------//
    //-Botones//
    //-------//
    
     @FXML
    private VBox vbox_botones;
    
    @FXML
    private Button guardar;
    @FXML
    private Button butt_nuevo;
    @FXML
    private Button butt_agregar;
    @FXML
    private Button butt_modificar;
    @FXML
    private Button butt_eliminar;
    @FXML
    private Button butt_cancelar;
    @FXML
    private Button butt_limpiar;
    
    
    @FXML
    private TextField text_ruc;
    @FXML
    private TextField text_descripcion;
    @FXML
    private TextField text_direccion;
    @FXML
    private TextField text_ciudad;
    @FXML
    private TextField text_estado;
    

    
    private void inicializar(){
        
        tablaProveedores.setEditable(true);
        
        tabcolp_estado.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("estado"));
        tabcolp_ciudad.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("ciudad"));
        tabcolp_direccion.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("direccion"));
        tabcolp_ruc.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("ruc"));
        tabcolp_descripcion.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("descripcion"));
        
        
         tablaProveedores.setOnMousePressed(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event){
            
                if(event.isPrimaryButtonDown() && event.getClickCount() == 2){
                    
                    Proveedor person = (Proveedor) tablaProveedores.getSelectionModel().getSelectedItem();
                        //System.out.println(person.getNombre());
                        
                        text_descripcion.setText(person.getDescripcion());
                        text_ciudad.setText(person.getCiudad());
                        text_direccion.setText(person.getDireccion());
                        text_ruc.setText(person.getRuc());
                        text_estado.setText(person.getEstado());
                        
                        
                        text_descripcion.setDisable(true);
                        text_ciudad.setDisable(true);
                        text_direccion.setDisable(true);
                        text_ruc.setDisable(true);
                        text_estado.setDisable(true);
                        
                        
                        butt_nuevo.setDisable(true);
                        butt_agregar.setDisable(true);
                        butt_limpiar.setDisable(true);
        
                        butt_eliminar.setDisable(false);
                        butt_modificar.setDisable(false);
                        butt_cancelar.setDisable(false);
                                            
                }
                
            }
        });
        
        text_ruc.setDisable(true);
        text_descripcion.setDisable(true);
        text_direccion.setDisable(true);
        text_ciudad.setDisable(true);
        text_estado.setDisable(true);
        
        
        butt_agregar.setDisable(true);
        butt_limpiar.setDisable(true);
        butt_modificar.setDisable(true);
        butt_eliminar.setDisable(true);
        butt_cancelar.setDisable(true);
    }
    
    
    @FXML
    public void buscarOnAction(ActionEvent event){
        
        //EmpleadosDB em = new EmpleadosDB();
        String nombreProveedor = buscar_proveedor.getText();
        proveedores = p.buscarProveedor(nombreProveedor);
        
        tablaProveedores.setItems(proveedores);
    }
    
    public void boton_terminar(ActionEvent event){
        
        proveedores = p.buscarProveedor("todos");

        tablaProveedores.setItems(proveedores);
        
        buscar_proveedor.clear();
    }
    
    public void butt_nueOnAction(ActionEvent event){
        
        butt_nuevo.setDisable(true);
        butt_agregar.setDisable(false);
        butt_limpiar.setDisable(false);
        //butt_modificar.setDisable(false);
        //butt_eliminar.setDisable(false);
        butt_cancelar.setDisable(false);
        
        text_ruc.setDisable(false);
        text_descripcion.setDisable(false);
        text_direccion.setDisable(false);
        text_ciudad.setDisable(false);
        text_estado.setDisable(false);
        
    }
    
    public void butt_agrgOnAction(ActionEvent event){
        
        String ruc, descripcion, direccion, ciudad, estado;
        estado = text_estado.getText();
        descripcion = text_descripcion.getText();
        direccion = text_direccion.getText();
        ruc = text_ruc.getText();
        ciudad = text_ciudad.getText();
        
        Proveedor nuevo = new Proveedor(ruc, descripcion, ciudad, estado, direccion);
        proveedores.add(nuevo);
        
        try{
            p.insertProveedor(nuevo);
        }
        catch(Exception e){
            
        }
        
        butt_limpiarOnAction(event);
        
    }
    
    public void butt_limpiarOnAction(ActionEvent event){
        
        text_ruc.clear();
        text_descripcion.clear();
        text_direccion.clear();
        text_estado.clear();
        text_ciudad.clear();
        
    }
    
    public void butt_elimOnAction(ActionEvent event){
        
        Proveedor proveeElim = Proveedor.buscarPorId(proveedores, text_ruc.getText());
        
        Stage secondaryStage = new Stage();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de Confirmación");
        alert.setContentText("¿ Seguro que desea eliminar ?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            
            secondaryStage.close();
            proveedores.remove(proveeElim);
            p.eliminarProveedor(proveeElim);
            butt_limpiarOnAction(event);
            
        } 
        else{
            
            alert.close();
            
        }
        
    }
    
    public void butt_modfOnAction(ActionEvent event){
        
        guardar = new Button("Guardar");
        guardar.getStyleClass().add("botones");
        vbox_botones.getChildren().add(guardar);
        
        text_ruc.setDisable(false);
        text_descripcion.setDisable(false);
        text_direccion.setDisable(false);
        text_ciudad.setDisable(false);
        text_estado.setDisable(false);
        
        guardar.setOnAction(ActionEvent -> guardar(event));
        
    }
    
    private void guardar(ActionEvent event){
        
        String ruc, descripcion, direccion, ciudad, estado;
        estado = text_estado.getText();
        descripcion = text_descripcion.getText();
        direccion = text_direccion.getText();
        ruc = text_ruc.getText();
        ciudad = text_ciudad.getText();
        
        Proveedor nuevo = new Proveedor(ruc, descripcion, ciudad, estado, direccion);
        
        p.modificarProveedor(nuevo);
        butt_limpiarOnAction(event);
        vbox_botones.getChildren().remove(guardar);
    }
    
    public void butt_cancelOnAction(){
        
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializar();
        
        proveedores = p.buscarProveedor("todos");
        
        tablaProveedores.setItems(proveedores);
    }    
    
}
