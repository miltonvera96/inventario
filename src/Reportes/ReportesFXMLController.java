/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import JasperReport.MostrarReporte;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ReportesFXMLController implements Initializable {

    String opcion;
    MostrarReporte mr = new MostrarReporte();
    
    @FXML
    private JFXButton mostrar;
    @FXML
    private JFXButton mostrar1;
    
    @FXML
    private JFXCheckBox empleados;
    @FXML
    private JFXCheckBox clientes;
    @FXML
    private JFXCheckBox productos;
    @FXML
    private JFXCheckBox ventas;
    @FXML
    private JFXComboBox<String> boxVentas;
    
    
    @FXML
    public void mostrarAction(ActionEvent evento) throws JRException, ClassNotFoundException{
        
        
        
        if(clientes.isSelected()){
            opcion = "Clientereport.jrxml";

        }
        else if(productos.isSelected()){
            opcion = "Productosenbodega.jrxml";
 
        }else if(empleados.isSelected()){
            opcion = "Empleados.jrxml";
  
        }
        
        
        mr.showReport(opcion);
        
    }
    
    EventHandler eh = new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof JFXCheckBox) {
            JFXCheckBox chk = (JFXCheckBox) event.getSource();
            System.out.println("Action performed on checkbox " + chk.getText());
            if ("Clientes".equals(chk.getText())) {
                productos.setSelected(!clientes.isSelected());
                empleados.setSelected(!clientes.isSelected());
                
            } else if ("Productos".equals(chk.getText())) {
                clientes.setSelected(!productos.isSelected());
                empleados.setSelected(!productos.isSelected());
                
            }else if ("Empleados".equals(chk.getText())) {
                clientes.setSelected(!empleados.isSelected());
                productos.setSelected(!empleados.isSelected());
            }
        }
    }
};

    
    private void inicializarBoxVentas(){
        
        ObservableList<String> tipos = FXCollections.observableArrayList("Ventas por Año", "Ventas por Empleado", "Productos Vendidos", "Ordenes de Venta");
        boxVentas.setItems(tipos);
        
    }
    
    @FXML
    public void mostrarAction2(ActionEvent evento) throws JRException, ClassNotFoundException{
        
        String value = boxVentas.getValue();
        
        if(value.equalsIgnoreCase("Ventas por Año")){
            
            this.opcion = "Ventasporanio.jrxml";
            
        }else if(value.equalsIgnoreCase("Ventas por Empleado")){
            
            this.opcion = "VentasEmpleado.jrxml";
            
        }else if(value.equalsIgnoreCase("Productos Vendidos")){
            
            this.opcion = "ProductosVendidos.jrxml";
            
        }else if(value.equalsIgnoreCase("Ordenes de Venta")){
            
            this.opcion = "Ordenes.jrxml";
        }
        
        mr.showReport(this.opcion);
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //checkBoxes();
        
        clientes.setOnAction(eh);
        productos.setOnAction(eh);
        empleados.setOnAction(eh);
        inicializarBoxVentas();
    }    
    
}
