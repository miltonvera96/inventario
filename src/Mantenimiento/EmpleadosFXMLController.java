
package Mantenimiento;

import BaseDatos.EmpleadosDB;
import BaseDatos.QueryEmpleados;
import Entidades.Alertas;
import Entidades.Empleado;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
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
public class EmpleadosFXMLController implements Initializable {

    EmpleadosDB e = new EmpleadosDB();
    ObservableList<Empleado> empleados;
    QueryEmpleados qr = new QueryEmpleados();
    
    
    //------//
    //-TABLA//
    //------//
    @FXML
    private TableView tablaEmpleados = new TableView<Empleado>();
    @FXML
    private TableColumn<Empleado, String> tabcol_nombre;
    @FXML
    private TableColumn<Empleado, String> tabcol_apellido;
    @FXML
    private TableColumn<Empleado, String> tabcol_idEmpleado;
    @FXML
    private TableColumn<Empleado, String> tabcol_cargo;
    @FXML
    private TableColumn<Empleado, String> tabcol_direccion;
    @FXML
    private TableColumn<Empleado, Integer> tabcol_estado;
    @FXML
    private TableColumn<Empleado, Float> tabcol_sueldo;
    
    
    @FXML
    private TabPane tabPane;
    
    
    
    //------//
    //-Ver--//
    //------//
    @FXML
    private TextField buscar_empleado;
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
    private TextField text_usuario;
    @FXML
    private TextField text_clave;
    @FXML
    private TextField text_id;
    @FXML
    private TextField text_apellido;
    @FXML
    private TextField text_direccion;
    @FXML
    private TextField text_sueldo;
    @FXML
    private TextField text_cargo;
    @FXML
    private ChoiceBox box_estado;
    @FXML
    private ComboBox box_departamentos;
    
    
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
        String nombreEmpleado = buscar_empleado.getText();
        empleados = e.buscarEmpleado(nombreEmpleado);
        
        tablaEmpleados.setItems(empleados);
    }
    
    
    
    public void butt_nueOnAction(ActionEvent event){
        
        butt_nuevo.setDisable(true);
        
        butt_agregar.setDisable(false);
        butt_eliminar.setDisable(true);
        butt_modificar.setDisable(true);
        butt_cancelar.setDisable(false);
        
        text_nombre.setDisable(false);
       text_apellido.setDisable(false);
       text_direccion.setDisable(false);
        text_sueldo.setDisable(false);
        text_cargo.setDisable(false);
        text_id.setDisable(false);
        
    }
    
    public void butt_agrgOnAction(ActionEvent event){
        
        String nombre, apellido, direccion, id, sueldo, cargo,estado;
        nombre = text_nombre.getText();
        apellido = text_apellido.getText();
        direccion = text_direccion.getText();
        id = text_id.getText();
        cargo = text_cargo.getText();
        sueldo = text_sueldo.getText();
        estado = box_estado.getValue().toString();

        
        try{
            
            Integer.parseInt(id);
            Empleado nuevo = new Empleado(id, nombre, apellido, direccion, estado,1, Float.parseFloat(sueldo), cargo, text_usuario.getText(), text_clave.getText());
            empleados.add(nuevo);
            qr.insertEmpleado(nuevo);
            butt_limpiarOnAction(event);
            
        }
        catch(Exception e){
            
            Alertas.infoBox("No se pudo Agregar!\n   1) Revisar campos introducidos", " Error al Agregar", "Alerta de Error");
            
        }
                
        
    }
    
    public void butt_limpiarOnAction(ActionEvent event){
        
        text_nombre.clear();
        text_apellido.clear();
        text_id.clear();
        text_cargo.clear();
        text_sueldo.clear();
        text_direccion.clear();
        text_usuario.clear();
        text_clave.clear();
        
    }
    
    public void butt_elimOnAction(ActionEvent event){
        
        Empleado empElim = Empleado.buscarPorId(empleados, text_id.getText());
        
        Stage secondaryStage = new Stage();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de Confirmación");
        alert.setContentText("¿ Seguro que desea eliminar ?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            
            secondaryStage.close();
            boolean hecho = qr.eliminarEmpleado(empElim);
            
            if(hecho){
                
                empleados.remove(empElim);
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
        text_apellido.setDisable(false);
        text_direccion.setDisable(false);
        text_sueldo.setDisable(false);
        text_cargo.setDisable(false);
        text_id.setDisable(false);
        
        butt_modificar.setDisable(true);
        
        guardar.setOnAction(ActionEvent -> guardar(event));
    }
    
    private void guardar(ActionEvent event){
        
        String nombre, apellido, direccion, id, sueldo, cargo,estado;
        nombre = text_nombre.getText();
        apellido = text_apellido.getText();
        direccion = text_direccion.getText();
        id = text_id.getText();
        cargo = text_cargo.getText();
        sueldo = text_sueldo.getText();
        estado = box_estado.getValue().toString();
        
        try{
            
            Integer.parseInt(id);
            Empleado nuevo = new Empleado(id, nombre, apellido, direccion, estado,1, Float.parseFloat(sueldo), cargo, text_usuario.getText(), text_clave.getText());
            qr.modificarEmpleado(nuevo);
            butt_limpiarOnAction(event);
            vbox_botones.getChildren().remove(guardar);
            
        }
        catch(Exception e){
            
            Alertas.infoBox("No se pudo modificar!\n   1) Revisar campos introducidos", "Modificación Errónea", "Alerta de Error");            
            System.out.print("no modificó");
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
        
        empleados = e.buscarEmpleado("todos");

        tablaEmpleados.setItems(empleados);
        
        buscar_empleado.clear();
    }
    
    private void inicializarTabla(TableView tabla){
        
        tabcol_nombre.setCellValueFactory(new PropertyValueFactory<Empleado,String>("nombre"));
        tabcol_apellido.setCellValueFactory(new PropertyValueFactory<Empleado,String>("apellido"));
        tabcol_direccion.setCellValueFactory(new PropertyValueFactory<Empleado,String>("direccion"));
        tabcol_idEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado,String>("idEmpleado"));
        tabcol_cargo.setCellValueFactory(new PropertyValueFactory<Empleado,String>("cargo"));
        tabcol_sueldo.setCellValueFactory(new PropertyValueFactory<Empleado,Float>("sueldo"));
        tabcol_estado.setCellValueFactory(new PropertyValueFactory<Empleado,Integer>("estado_Activo"));
        
         tablaEmpleados.setOnMousePressed(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event){
            
                if(event.isPrimaryButtonDown() && event.getClickCount() == 2){
                    
                    Empleado person = (Empleado) tablaEmpleados.getSelectionModel().getSelectedItem();
                        System.out.println(person.getNombre());
                        
                        text_nombre.setText(person.getNombre());
                        text_apellido.setText(person.getApellido());
                        text_direccion.setText(person.getDireccion());
                        text_sueldo.setText(Float.toString(person.getSueldo()));
                        text_cargo.setText(person.getCargo());
                        text_id.setText(person.getIdEmpleado());
                        text_usuario.setText(person.getUsuario());
                        text_clave.setText(person.getPassword());
                        box_estado.setValue(person.getEstado_Activo());
                        
                        text_nombre.setDisable(true);
                        text_apellido.setDisable(true);
                        text_direccion.setDisable(true);
                        text_sueldo.setDisable(true);
                        text_cargo.setDisable(true);
                        text_id.setDisable(true);
                        
                        
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
        
        ObservableList<String> estados = FXCollections.observableArrayList("Activo","No Activo");
        box_estado.setItems(estados);
        
//        ObservableList<String> departamentos = FXCollections.observableArrayList("Departamento 1", "Departamento 2", "Departamento 3");
//        box_departamentos.setItems(departamentos);
//        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        inicializarBotones();
        
        tablaEmpleados.setEditable(true);
        
        inicializarTabla(tablaEmpleados);
        
        empleados = e.buscarEmpleado("todos");

        tablaEmpleados.setItems(empleados);
        
        inicializarBox();


    }    
    
}
