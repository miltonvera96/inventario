package Login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BaseDatos.*;
import Entidades.Empleado;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class LoginFXMLController implements Initializable {
    
    @FXML 
    private Label labtitulo;
    @FXML 
    private JFXTextField textuser;
    @FXML 
    private JFXPasswordField textcont;
    @FXML 
    private Label labuser;
    @FXML 
    private Label labcont;
    @FXML 
    private Label labelerror;
        
    ConexionDB c = new ConexionDB();
    Connection conn;
    
    public Empleado autentificarEmpleado(ActionEvent e) throws IOException{
        
        Empleado empleado = null;
        
        if (textuser.getText().equals("")) {
            this.labelerror.setText("Ingresar nombre de usuario");
            this.textuser.requestFocus();
            return null ;
        }

        if (textcont.getText().equals("")) {
            this.labelerror.setText("Ingresar contraseña");
            this.textcont.requestFocus();
            return null;
        }
        
        empleado = EmpleadosDB.validarEmpleado(textuser.getText(), textcont.getText());
        
        System.out.print(empleado.getApellido() + empleado.getPassword());
        
        if(empleado.getPassword().equals(textcont.getText()) && empleado.getAdministrador()==1){
            
            System.out.println("hola" + empleado.getNombre());
            
            Stage stage = (Stage) ((Node)(e.getSource())).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Inicio/Inicio.fxml"));
            
            Scene scene = new Scene(root, 1420, 800);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX(0); 
            stage.setY(0);  
            stage.setTitle("Inicio del Sistema");
            stage.setScene(scene);
            stage.show();
            
        }
        else {
            if(empleado.getAdministrador() != 1)
                this.labelerror.setText("No tiene permiso para ingresar ");
            else
                this.labelerror.setText("Contraseña incorrecta");
        }
        return empleado;
    }
    
    @FXML
    private void buting(ActionEvent event) throws SQLException, IOException {

        autentificarEmpleado(event);
        
        
    }
    

    
    
    
    @FXML
    private void butlim(ActionEvent event) {
        
        textuser.clear();
        textcont.clear();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}
