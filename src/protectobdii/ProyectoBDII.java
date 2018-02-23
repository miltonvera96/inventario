package protectobdii;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 *
 * @author Usuario
 */
public class ProyectoBDII extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/Login/LoginFXML.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        //stage.setX((stage.getWidth()) / 2); 
        //stage.setY((stage.getHeight()) / 4);  
        
        stage.show();
//        Parent root;
//        Scene scene;
//        
//        root = FXMLLoader.load(getClass().getResource("/Login/Cargando.fxml"));
//        scene = new Scene(root);
//        stage.setTitle("Sistema de Bases de Datos");
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setResizable(false);
//        stage.centerOnScreen();
//        stage.setScene(scene);
//        stage.show();
    }


    
    public static void main(String[] args) {
        Application.launch(ProyectoBDII.class, (java.lang.String[]) null);
//launch(args);

    }
    
}
