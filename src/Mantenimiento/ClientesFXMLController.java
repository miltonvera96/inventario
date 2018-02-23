/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import BaseDatos.ClientesDB;
import BaseDatos.QueryEmpleados;
import Entidades.Alertas;
import Entidades.Cliente;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
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
public class ClientesFXMLController implements Initializable {

    QueryEmpleados qr = new QueryEmpleados();
    
    ClientesDB c = new ClientesDB();
    ObservableList<Cliente> clientes;
    
    
    //------//
    //-TABLA//
    //------//

    
    @FXML
    private TableView tablaClient = new TableView<Cliente>();
    @FXML
    private TableColumn<Cliente, String> tabcolc_nombre;
    @FXML
    private TableColumn<Cliente, String> tabcolc_telefono;
    @FXML
    private TableColumn<Cliente, String> tabcolc_ruc;
    @FXML
    private TableColumn<Cliente, String> tabcolc_ciudad;
    @FXML
    private TableColumn<Cliente, String> tabcolc_direccion;
    @FXML
    private TableColumn<Cliente, Integer> tabcolc_codigo;
    @FXML
    private TableColumn<Cliente, String> tabcolc_tipo;
    
    @FXML
    private TabPane tabPane;
    
    
    
    //------//
    //-Ver--//
    //------//
    @FXML
    private TextField buscar_cliente;
    @FXML
    private Button boton_bucar;
    @FXML
    private Button boton_terminar;
    
    
    //------//
    //-Editar//
    //------//
    
    @FXML
    private TextField text_nombre;
    @FXML
    private TextField text_ruc;
    @FXML
    private TextField text_telefono;
    @FXML
    private TextField text_direccion;
    @FXML
    private TextField text_ciudad;
    @FXML
    private TextField text_codigo;
    @FXML
    private ComboBox box_tipos;
    
    
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
    
    
    //--------//
    //-Acciones//
    //--------//
    
    @FXML
    public void buscarOnAction(ActionEvent event){
        
        //EmpleadosDB em = new EmpleadosDB();
        String nombreCliente = buscar_cliente.getText();
        clientes = c.buscarCliente(nombreCliente);
        
        tablaClient.setItems(clientes);
    }
    
    
    
    public void butt_nueOnAction(ActionEvent event){
        
        int codigo = clientes.size() + 1;
        text_codigo.setText("c" + Integer.toString(codigo));
        text_codigo.setDisable(true);
        
        butt_nuevo.setDisable(true);
        
        butt_agregar.setDisable(false);
        butt_eliminar.setDisable(true);
        butt_modificar.setDisable(true);
        butt_cancelar.setDisable(false);
        
    }
    
    public void butt_agrgOnAction(ActionEvent event){
        
        String nombre, telefono, direccion, ruc, ciudad, tipo, codigo;
        codigo = text_codigo.getText();
        nombre = text_nombre.getText();
        telefono = text_telefono.getText();
        direccion = text_direccion.getText();
        ruc = text_ruc.getText();
        ciudad = text_ciudad.getText();
        tipo = box_tipos.getValue().toString();
        
        
        
        try{
            
            Integer.parseInt(ruc);
            Cliente nuevo = new Cliente(codigo,ruc, nombre, telefono, ciudad, direccion ,tipo);
            clientes.add(nuevo);
            c.insertCliente(nuevo);
            butt_limpiarOnAction(event);
        }
        catch(Exception e){
            
            Alertas.infoBox("No se pudo Agregar!\n   1) Revisar campos introducidos", " Error al Agregar", "Alerta de Error");
            
        }
                
    }
    
    public void butt_limpiarOnAction(ActionEvent event){
        
        text_nombre.clear();
        text_telefono.clear();
        text_ruc.clear();
        text_ciudad.clear();
        text_direccion.clear(); 
        text_codigo.clear();
        
    }
    
    public void butt_elimOnAction(ActionEvent event){
        
        //Cliente clienElim = Cliente.buscarPorId(clientes, text_codigo.getText());
        
        Stage secondaryStage = new Stage();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de Confirmación");
        alert.setContentText("¿ Seguro que desea eliminar ?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            
            boolean hecho;
            secondaryStage.close();
            hecho = c.eliminarCliente(text_codigo.getText());
            if(hecho){
                //clientes.remove(clienElim);
                butt_limpiarOnAction(event);
            }
            
        } 
        else{
            
            alert.close();
        }
        
        
    }
    
    public void butt_modfOnAction(ActionEvent event){
        
        guardar = new Button("Guardar");
        guardar.getStyleClass().add("botones");
        vbox_botones.getChildren().add(guardar);
        
        text_nombre.setDisable(false);
        text_ciudad.setDisable(false);
        text_direccion.setDisable(false);
        text_ruc.setDisable(false);
        text_telefono.setDisable(false);
        
        butt_modificar.setDisable(true);

        guardar.setOnAction(ActionEvent -> guardar(event));
        
    }
    
    private void guardar(ActionEvent event){
        
        String nombre, telefono, direccion, ruc, ciudad, tipo, codigo;
        codigo = text_codigo.getText();
        nombre = text_nombre.getText();
        telefono = text_telefono.getText();
        direccion = text_direccion.getText();
        ruc = text_ruc.getText();
        ciudad = text_ciudad.getText();
        tipo = box_tipos.getValue().toString();
        
        try{
            
            Integer.parseInt(ruc);
            Cliente nuevo = new Cliente(codigo,ruc, nombre, telefono, ciudad, direccion ,tipo);
            c.modificarCliente(nuevo);
            butt_limpiarOnAction(event);
            vbox_botones.getChildren().remove(guardar);
            
        }
        catch(Exception e){
            
            Alertas.infoBox("No se pudo modificar!\n   1) Revisar campos introducidos", "Modificación Errónea", "Alerta de Error");            
            
        }
        

    }
    
    public void butt_cancelOnAction(ActionEvent event){
        
        butt_nuevo.setDisable(false);
        butt_agregar.setDisable(true);
        butt_eliminar.setDisable(true);
        butt_modificar.setDisable(true);
        butt_cancelar.setDisable(true);
        
        butt_limpiarOnAction(event);

        tabPane.getSelectionModel().select(0);
        
    }
    
    
    public void boton_terminar(ActionEvent event){
        
        clientes = c.buscarCliente("todos");

        tablaClient.setItems(clientes);
        
        buscar_cliente.clear();
    }
    
    private void inicializarTabla(TableView tabla){
        
        tabcolc_nombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
        tabcolc_telefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
        tabcolc_direccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
        tabcolc_ruc.setCellValueFactory(new PropertyValueFactory<Cliente,String>("ruc"));
        tabcolc_ciudad.setCellValueFactory(new PropertyValueFactory<Cliente,String>("ciudad"));
        tabcolc_tipo.setCellValueFactory(new PropertyValueFactory<Cliente,String>("tipo"));
        tabcolc_codigo.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("codigo"));
        
        
        tablaClient.setOnMousePressed(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event){
            
                if(event.isPrimaryButtonDown() && event.getClickCount() == 2){
                    
                    Cliente person = (Cliente) tablaClient.getSelectionModel().getSelectedItem();
                        System.out.println(person.getNombre());
                        
                        text_nombre.setText(person.getNombre());
                        text_ciudad.setText(person.getCiudad());
                        text_direccion.setText(person.getDireccion());
                        text_codigo.setText(person.getCodigo());
                        text_ruc.setText(person.getRuc());
                        text_telefono.setText(person.getTelefono());
                        box_tipos.setValue(person.getTipo());
                        
                        
                        text_nombre.setDisable(true);
                        text_ciudad.setDisable(true);
                        text_direccion.setDisable(true);
                        text_codigo.setDisable(true);
                        text_ruc.setDisable(true);
                        text_telefono.setDisable(true);
                        
                        
                        butt_nuevo.setDisable(true);
                        butt_agregar.setDisable(true);
                        butt_limpiar.setDisable(true);
        
                        butt_eliminar.setDisable(false);
                        butt_modificar.setDisable(false);
                        butt_cancelar.setDisable(false);
                        
                        tabPane.getSelectionModel().select(1);
                    
                }
                
            }
        });
         
         
        
    }
    
    
    public void inicializarBotones(){
        
        butt_agregar.setDisable(true);
        butt_eliminar.setDisable(true);
        butt_modificar.setDisable(true);
        butt_cancelar.setDisable(true);
    }
    
    
    public void inicializarBox(){
        
        ObservableList<String> departamentos = FXCollections.observableArrayList("normal", "publico", "privado");
        box_tipos.setItems(departamentos);
        
    }
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        inicializarBotones();
        
        tablaClient.setEditable(true);
        
        inicializarTabla(tablaClient);
        
        
        //------------------------------
        clientes = c.buscarCliente("todos");

        tablaClient.setItems(clientes);
        
        inicializarBox();


    }  
   
}
