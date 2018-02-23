/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organizacion;

import BaseDatos.BodegaDB;
import BaseDatos.ProductosDB;
import Entidades.Bodega;
import Entidades.Producto;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class AlmacenFXMLController implements Initializable {

    
    ProductosDB pro = new ProductosDB();

    ObservableList<Producto> productos;
    Producto temp;
    
    BodegaDB bo = new BodegaDB();
    ObservableList<Bodega> bodegas;
    
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
    private TableColumn<Producto, String> tabcolal_bodega;
    @FXML
    private TableColumn<Producto, Integer> tabcolal_cantidad;
    
     //--------------//
    //Tabla Bodega//
    //-------------//
    
    @FXML
    private TableView tablaBodega = new TableView<Bodega>();
    @FXML
    private TableColumn<Bodega, String> tabcolb_codigo;
    @FXML
    private TableColumn<Bodega, String> tabcolb_tipo;
    @FXML
    private TableColumn<Bodega, String> tabcolb_direccion;
   
    
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

    public void setProducto(Producto producto) {
        this.productos.add(producto);
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
        
         
        
    }
    
    public void retornarProducto(){
        
                tablaProductos.setOnMousePressed(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event){
            
                if(event.isPrimaryButtonDown() && event.getClickCount() == 2){
                    
                    
                   
                    try {
                        Producto producto = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
                        
                        temp = producto;
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Organizacion/ProductoFXML.fxml"));

                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setScene( new Scene((Pane)loader.load()));
                        ProductoFXMLController controller = loader.<ProductoFXMLController>getController();
                        controller.initData(producto);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AlmacenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.print("asasd");
                    }
                    

                    
                   
                }
                
            }
        });
        
    }
    
    
    public void agregarProductoAction(ActionEvent event) throws IOException{
        
//        try{
            Parent loader = FXMLLoader.load(getClass().getResource("/Organizacion/ProductoFXML.fxml"));
        
            Stage stage = new Stage();
            stage.setTitle("Nuevo Producto");
            stage.setScene(new Scene(loader,800,470));
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
//        }
//        catch(Exception e){
//            
//        }
    }
    
    
    public void inicializarTablaBodega(){
        
        tabcolb_codigo.setCellValueFactory(new PropertyValueFactory<Bodega,String>("codigo"));
        tabcolb_tipo.setCellValueFactory(new PropertyValueFactory<Bodega,String>("tipo"));
        tabcolb_direccion.setCellValueFactory(new PropertyValueFactory<Bodega,String>("direccion"));
        
        tablaBodega.setOnMousePressed(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event){
            
                if(event.isPrimaryButtonDown() && event.getClickCount() == 2){
                    
                    Bodega bodega = (Bodega) tablaBodega.getSelectionModel().getSelectedItem();
                        
                    productos = pro.buscarPorBodega(bodega.getCodigo());
        
                    tablaProductos.setItems(productos);    
                        //tabPane.getSelectionModel().select(1);
                    
                }
                
            }
        });
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        iniciarTablaProducto();
        retornarProducto();
        
        tablaProductos.setEditable(true);
        
        productos = pro.buscarProducto("todos");

        tablaProductos.setItems(productos);
        
        inicializarTablaBodega();
                
        tablaBodega.setEditable(true);
        
        bodegas = bo.buscarBodega("todos");

        tablaBodega.setItems(bodegas);
    }    
    
}
