/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenes;

import BaseDatos.DetalleVentaDB;
import BaseDatos.OrdenVentaDB;
import Entidades.Bodega;
import Entidades.DetalleVenta;
import Entidades.OrdenVenta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class BuscaOFXMLController implements Initializable {
    
    OrdenVentaDB ovDB = new OrdenVentaDB();
    ObservableList<OrdenVenta> ordenes;
    
    DetalleVentaDB dvDB = new DetalleVentaDB();
    ObservableList<DetalleVenta> ventasOrdenes;
    
    
     //---------//
    //Busqueda-//
    //---------//
    @FXML
    private TextField codigoOrden;
    @FXML
    private ChoiceBox<String> opcionAnio;
    @FXML
    private ChoiceBox<String> opcionMeses;
    
    private void setOpciones(){
        
        opcionAnio.getItems().addAll("-Any-", "2014","2015", "2016", "2017", "2018", "2019" );
        opcionMeses.getItems().addAll("-Any-", "01","02", "03", "04", "05", "06","07","08", "09", "10", "11", "12" );
    }
    
     //--------------//
    //Tabla OVenta-//
    //-------------//
    
     @FXML
    private TableView ordenesVenta = new TableView<OrdenVenta>();
    @FXML
    private TableColumn<OrdenVenta, String> idorden;
    @FXML
    private TableColumn<OrdenVenta, String> idcliente;
    @FXML
    private TableColumn<OrdenVenta, String> idvendedor;
    @FXML
    private TableColumn<OrdenVenta, String> fecha;
    @FXML
    private TableColumn<OrdenVenta, Float> tabcol_total;
    
     //--------------//
    //Tabla OVenta-//
    //-------------//
    
    @FXML
    private TableView tabViewdetalleVenta = new TableView<DetalleVenta>();
    @FXML
    private TableColumn<DetalleVenta, String> idordenDV;
    @FXML
    private TableColumn<DetalleVenta, String> idproducto;
    @FXML
    private TableColumn<DetalleVenta, String> descripcion;
    @FXML
    private TableColumn<DetalleVenta, Float> precio;
    @FXML
    private TableColumn<DetalleVenta, Integer> cantidad;
    
    
    
    @FXML
    private void iniciarTablaOrdenes(){
            
        ordenesVenta.setEditable(true); 
        
        idorden.setCellValueFactory(new PropertyValueFactory<>("idorden"));
        idcliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        idvendedor.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tabcol_total.setCellValueFactory(new PropertyValueFactory<>("total"));
            
        ordenesVenta.setOnMousePressed(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event){
            
                if(event.isPrimaryButtonDown() && event.getClickCount() == 2){
                    
                    OrdenVenta orden = (OrdenVenta) ordenesVenta.getSelectionModel().getSelectedItem();
                        
                    ventasOrdenes = dvDB.buscarPorNOrden(orden.getIdorden());
        
                    tabViewdetalleVenta.setItems(ventasOrdenes);    
                        //tabPane.getSelectionModel().select(1);
                    
                }
                
            }
        });
     }
    
     @FXML
    private void iniciarTablaDetalleVenta(){
            
        tabViewdetalleVenta.setEditable(true); 
        
        idordenDV.setCellValueFactory(new PropertyValueFactory<>("idorden"));
        idproducto.setCellValueFactory(new PropertyValueFactory<>("idproducto"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    }
    
    @
    FXML
    public void buscarOnAction(ActionEvent event){
        
        String anio = opcionAnio.getValue();
        String mes = opcionMeses.getValue();
        String codigo = codigoOrden.getText();
        
        if(mes.equals("-Any-"))
            mes = "";
        
        if(anio.equals("-Any-"))
            anio = "";
//        if(!codigo.equals("")){
//            
//        }

         ordenes = ovDB.buscarOrdenFecha(codigo, anio, mes);
         ordenesVenta.setItems(ordenes);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        setOpciones();
        opcionAnio.setValue("-Any-");
        opcionMeses.setValue("-Any-");
        
        iniciarTablaOrdenes();
        ordenes = ovDB.buscarOrden("todos");
        
        ordenesVenta.setItems(ordenes);
        
        iniciarTablaDetalleVenta();
    }    
    
}
