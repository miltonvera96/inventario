/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenes;

import BaseDatos.ClientesDB;
import BaseDatos.DetalleVentaDB;
import BaseDatos.OrdenVentaDB;
import BaseDatos.ProductosDB;
import Entidades.Alertas;
import Entidades.Cliente;
import Entidades.DetalleVenta;
import Entidades.OrdenVenta;
import Entidades.Producto;
import Inicio.Fecha;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class OrdenesFXMLController implements Initializable {

    int countventa = 0;
    
    Fecha f = new Fecha();
    
    ClientesDB c = new ClientesDB();
    
    ProductosDB pro = new ProductosDB();
    
    DetalleVentaDB dv = new DetalleVentaDB();
    int cantDV = dv.cantidadDetalles();
    
    OrdenVentaDB ov = new OrdenVentaDB();
    
    ObservableList<Producto> productos;
    
    List<DetalleVenta> lista = new LinkedList();
    
    ObservableList<DetalleVenta> detalles = FXCollections.observableArrayList(lista);
    //Producto temp;
    
    ObservableList<OrdenVenta> ordenes;
    
    
    /*
     Datos Cliente
     */
    
    @FXML
    private TextField nombre_cliente;
    @FXML
    private TextField ruc_cliente;
    @FXML
    private TextField telefono_cliente;
    @FXML
    private TextField direccion_cliente;
    @FXML
    private TextField ciudad_cliente;
    @FXML
    private TextField tipo_cliente;
    
    /*
     Datos Vendedor
     */
    
    @FXML
    private TextField nombre_vendedor;
    @FXML
    private TextField ruc_vendedor;
    
    /*
     Datos Orden
     */
    @FXML
    private TextField numero_orden;
    @FXML
    private TextField fecha_orden;
    
    /*
     Datos factura
     */
    @FXML
    private TextField subtotal;
    @FXML
    private TextField iva;
    @FXML
    private TextField total;
    
    
     //--------------//
    //Tabla DVenta-//
    //-------------//
    @FXML
    private TableView TablaDetalleVenta = new TableView<DetalleVenta>();
    @FXML
    private TableColumn<DetalleVenta, String> idproducto;
    @FXML
    private TableColumn<DetalleVenta, String> descripcion;
    @FXML
    private TableColumn<DetalleVenta, Float> precioun;
    @FXML
    private TableColumn<DetalleVenta, Integer> cantidad;
    
    
     //--------------//
    //Tabla Producto//
    //-------------//
    
    @FXML
    private TableView tablaProductos = new TableView<Producto>();
    @FXML
    private TableColumn<Producto, String> tabcolal_codigo;
    @FXML
    private TableColumn<Producto, String> tabcolal_descripcion;
    @FXML
    private TableColumn<Producto, String> tabcolal_tipo;
    @FXML
    private TableColumn<Producto, String> tabcolal_linea;
    @FXML
    private TableColumn<Producto, String> tabcolal_categoria;
    @FXML
    private TableColumn<Producto, Float> tabcolal_precio;
    @FXML
    private TableColumn<Producto, Integer> tabcolal_cantidad;
    @FXML
    private TableColumn<Producto, String> tabcolal_bodega;
    
    
    //------//
    //Buscar//
    //------//
    @FXML
    private TextField buscar_producto;
    @FXML
    private Button boton_bucar;
    @FXML
    private Button boton_terminar;

    @FXML
    private Button cancelar_button;
    @FXML
    private Button realizar_orden;
    
    @FXML
    public void buscarOnAction(ActionEvent event){
        
        if(!buscar_producto.getText().equals("")){
        String nombrepoduct = buscar_producto.getText();
        productos = pro.buscarProducto(nombrepoduct);
        
        tablaProductos.setItems(productos);
        }
        else{
            Stage secondaryStage = new Stage();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setContentText("Ingrese Texto para buscar");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                secondaryStage.close();
                        //addSalida(pane);
            } else{
                alert.close();
            }
        }
        
    }
    
    @FXML
    public void boton_terminar(){
        
        productos = pro.buscarProducto("todos");
        
        tablaProductos.setItems(productos);
        
    }
    
    
     private void iniciarTablaProducto(){
            
         
        tabcolal_codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tabcolal_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tabcolal_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tabcolal_categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tabcolal_linea.setCellValueFactory(new PropertyValueFactory<>("linea"));
        tabcolal_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tabcolal_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));        
        tabcolal_bodega.setCellValueFactory(new PropertyValueFactory<>("bodega"));

       tablaProductos.setOnMousePressed(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event){
            
                if(event.isPrimaryButtonDown() && event.getClickCount() == 2){

                        Producto producto = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
                        
                        cantDV++;
                        DetalleVenta nuevo = new DetalleVenta("dv" + Integer.toString(cantDV), producto.getCodigo(), producto.getDescripcion(), producto.getPrecio(), 1, numero_orden.getText());
                   
                        detalles.add(nuevo);
                        //lista.add(nuevo);

                    
                   
                }
                
            }
        });
                
        
        
    }
     
     private void iniciarTablaDetalle(){
            
        TablaDetalleVenta.setEditable(true); 
        
        idproducto.setCellValueFactory(new PropertyValueFactory<>("idproducto"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioun.setCellValueFactory(new PropertyValueFactory<>("precio"));
        cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        


        cantidad.setCellFactory(TextFieldTableCell.<DetalleVenta, Integer>forTableColumn(new IntegerStringConverter()));
        cantidad.setOnEditCommit(
            new EventHandler<CellEditEvent<DetalleVenta, Integer>>() { 
                @Override
                public void handle(CellEditEvent<DetalleVenta, Integer> t) {
                    ((DetalleVenta) t.getTableView().getItems().get(
                            t.getTablePosition().getRow()) 
                            ).setCantidad(t.getNewValue());
                } 
            }
        );
        
        
        
     }
     
    @FXML
    public void realizarOrdenAction(ActionEvent event) throws SQLException, ClassNotFoundException{
        
        String nombre, telefono, direccion, ruc, ciudad, tipo, codigo;
        int count;
        
        count = c.cantidadClientes();
        codigo  = "c" + Integer.toString(count + 1);
        nombre = nombre_cliente.getText();
        telefono = telefono_cliente.getText();
        direccion = direccion_cliente.getText();
        ruc = ruc_cliente.getText();
        ciudad = nombre_cliente.getText();
        tipo = tipo_cliente.getText();
        
        Cliente nuevoCl = new Cliente(codigo,ruc, nombre, telefono, direccion, ciudad ,tipo);
        
        OrdenVenta nuevo = new OrdenVenta(numero_orden.getText(), fecha_orden.getText(), nuevoCl.getCodigo(), ruc_vendedor.getText(), Float.parseFloat(total.getText()));
        System.out.println(nuevo.getFecha() + " "+ nuevo.getIdCliente() + " "+ nuevo.getIdEmpleado()+  " "+ nuevo.getIdorden());
        
        try{
            
            if(c.insertCliente(nuevoCl)){
                ov.insertOrdenVenta(nuevo);
                dv.insertList(detalles);           

                Stage stage = (Stage) realizar_orden.getScene().getWindow();
                stage.close();
            }
        }
        catch(Exception e){
            
        }
        
    }
    
    public void agregarCliente(){
        
        String nombre, telefono, direccion, ruc, ciudad, tipo, codigo;
        int cant = c.cantidadClientes();
        codigo = "c" + Integer.toString(cant + 1);
        System.out.println("Codigo de cliente" + codigo);
        nombre = nombre_cliente.getText();
        telefono = telefono_cliente.getText();
        direccion = direccion_cliente.getText();
        ruc = ruc_cliente.getText();
        ciudad = ciudad_cliente.getText();
        tipo = tipo_cliente.getText();
        
        
        try{
            Integer.parseInt(ruc);
            Cliente nuevo = new Cliente(codigo,ruc, nombre, telefono, ciudad, direccion ,tipo);
            c.insertCliente(nuevo);
        }
        catch(Exception e){
            Alertas.infoBox("No se pudo Agregar!\n   1) Revisar campos introducidos", " Error al Agregar", "Alerta de Error");
            
        }
        
    }
    
        public void setNumeroOrden(){
        
        int nextOV = ov.cantidadOrdenes() + 1;
        String numero = "";
        
        if(nextOV < 10){
            numero = "OV00" + Integer.toString(nextOV);
        }else if(nextOV < 100){
            numero = "OV0" + Integer.toString(nextOV);
        }else if(nextOV < 1000){
            numero = "OV" + Integer.toString(nextOV);
        }
        
        numero_orden.setText(numero);
        numero_orden.setDisable(true);
    }
        
     public void calcularTotal(ActionEvent event){
         
         Float total1 = 0f;
         Float iva1  = 0.14f;
         
        for(Object item : TablaDetalleVenta.getItems()) {
            DetalleVenta item2  = (DetalleVenta) item;
           total1 = total1 + (item2.getPrecio() * item2.getCantidad());
        }
        subtotal.setText(Float.toString(total1));
        iva.setText(Float.toString(iva1));
        total.setText(Float.toString(total1 + (total1 * iva1)));
     }
     
     public void cancelarOrden(ActionEvent event){
         
         Stage secondaryStage = new Stage();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setContentText("¿ Está seguro que desea cancelar ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                secondaryStage.close();
                Stage stage = (Stage) cancelar_button.getScene().getWindow();
                stage.close();
            } else{
                alert.close();
            }
         
     }
     
     public void borrarDOrden(ActionEvent event){
         
         DetalleVenta detalle = (DetalleVenta) TablaDetalleVenta.getSelectionModel().getSelectedItem();
         detalles.remove(detalle);
         
     }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        fecha_orden.setText(f.getFechaNumero());
        iniciarTablaProducto();
        productos = pro.buscarProducto("todos");

        tablaProductos.setItems(productos);
        //numero_orden.setText("ov" + Integer.toString(ordenes.size()));
        
        iniciarTablaDetalle();
        TablaDetalleVenta.setItems(detalles);
        
        setNumeroOrden();
    }    
    
}
