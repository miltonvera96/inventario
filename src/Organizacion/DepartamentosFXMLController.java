/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organizacion;

import BaseDatos.DepartamentosDB;
import Entidades.Alertas;
import Entidades.Departamento;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class DepartamentosFXMLController implements Initializable {

    
 //------------------------
    DepartamentosDB d = new DepartamentosDB();
    ObservableList<Departamento> departamentos;
    
    
    //------//
    //-TABLA//
    //------//

    
    @FXML
    private TableView tablaDepa = new TableView<Departamento>();
    @FXML
    private TableColumn<Departamento, String> tabcold_nombre;
    @FXML
    private TableColumn<Departamento, Integer> tabcold_codigo;
    @FXML
    private TableColumn<Departamento, String> tabcold_nomina;
    
    @FXML
    private TabPane tabPane;
    
    
    
    //------//
    //-Ver--//
    //------//
    @FXML
    private TextField buscar_depa;
    @FXML
    private Button boton_bucar;
    @FXML
    private Button boton_terminar;
    
    
    //------//
    //-Editar//
    //------//
    
    @FXML
    private TextField text_codigo;
    @FXML
    private TextField text_nombre;
    @FXML
    private TextField text_nomina;

    
    
    @FXML
    private Button butt_nuevo;
    @FXML
    private Button butt_agregar;
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
        String nombreDepa = buscar_depa.getText();
        departamentos = d.buscarDepartamento(nombreDepa);
        
        tablaDepa.setItems(departamentos);
    }
    
    
    
    public void butt_nueOnAction(ActionEvent event){
        
        int codigo = departamentos.size() + 1;
        text_codigo.setText(Integer.toString(codigo));
        text_codigo.setDisable(true);
        
        butt_nuevo.setDisable(true);
        
        butt_agregar.setDisable(false);
        butt_eliminar.setDisable(true);
        butt_cancelar.setDisable(false);
        
        text_nombre.setDisable(false);
        text_nomina.setDisable(false);
        
    }
    
    public void butt_agrgOnAction(ActionEvent event){
        
        String nombre, nomina, codigo;
        codigo = text_codigo.getText();
        nombre = text_nombre.getText();
        nomina = text_nomina.getText();

        
        Departamento nuevo = new Departamento(Integer.parseInt(codigo), nombre,nomina);
        departamentos.add(nuevo);
        
        try{
            d.insertDepartamento(nuevo);
        }
        catch(Exception e){
            
        }
        
        butt_limpiarOnAction(event);
//        
        
    }
    
    public void butt_limpiarOnAction(ActionEvent event){
        
        text_codigo.clear();
        text_nombre.clear();
        text_nomina.clear();
        
    }
    
    public void butt_elimOnAction(ActionEvent event){
        
        Departamento depaElim = Departamento.buscarPorId(departamentos, Integer.parseInt(text_codigo.getText()));
        departamentos.remove(depaElim);
        
        d.eliminarDepartamento(depaElim);
        
        butt_limpiarOnAction(event);
        
    }
    
    public void butt_modfOnAction(ActionEvent event){
        
        text_nombre.setDisable(false);
        text_codigo.setDisable(false);
        text_nomina.setDisable(false);

        
    }
    
    public void butt_cancelOnAction(ActionEvent event){
        
        butt_nuevo.setDisable(false);
        butt_agregar.setDisable(true);
        butt_eliminar.setDisable(true);
        butt_cancelar.setDisable(true);
        
        butt_limpiarOnAction(event);

        
    }
    
    
    public void boton_terminar(ActionEvent event){
        
        departamentos = d.buscarDepartamento("todos");

        tablaDepa.setItems(departamentos);
        
        buscar_depa.clear();
    }
    
    private void inicializarTabla(TableView tabla){
        
        tabcold_nombre.setCellValueFactory(new PropertyValueFactory<Departamento,String>("nombre"));
        tabcold_nomina.setCellValueFactory(new PropertyValueFactory<Departamento,String>("nomina"));
        tabcold_codigo.setCellValueFactory(new PropertyValueFactory<Departamento,Integer>("codigo"));
        
        
        tablaDepa.setOnMousePressed(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event){
            
                if(event.isPrimaryButtonDown() && event.getClickCount() == 2){
                    
                    Departamento depa = (Departamento) tablaDepa.getSelectionModel().getSelectedItem();
                        System.out.println(depa.getNombre());
                        
                        text_nomina.setText(depa.getNomina());
                        text_codigo.setText(Integer.toString(depa.getCodigo()));
                        text_nombre.setText(depa.getNombre());
                        
                        
                        text_codigo.setDisable(true);
                        text_nomina.setDisable(true);
                        text_nombre.setDisable(true);
                        
                        
                        butt_nuevo.setDisable(true);
                        butt_agregar.setDisable(true);
                        butt_limpiar.setDisable(true);
        
                        butt_eliminar.setDisable(false);
                        butt_cancelar.setDisable(false);
                        
                        //tabPane.getSelectionModel().select(1);
                    
                }
                
            }
        });
         
         
        
    }
    
    
    public void inicializarBotones(){
        
        butt_agregar.setDisable(true);
        butt_eliminar.setDisable(true);
        butt_cancelar.setDisable(true);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        inicializarBotones();
        
        tablaDepa.setEditable(true);
        
        inicializarTabla(tablaDepa);
        
        
//        empleados = e.buscarEmpleado("todos");
//
//        tablaEmpleados.setItems(empleados);
        
        //------------------------------
        departamentos = d.buscarDepartamento("todos");

        tablaDepa.setItems(departamentos);
        


    }    
        
    
}
