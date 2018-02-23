/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Administrador
 */
public class CargandoController implements Initializable {

    @FXML
    public ProgressBar carg_bar;
     Progreso pro;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carg_bar.setProgress(0);
        pro = new Progreso(carg_bar);
        pro.run();
        
        try {
            Cambiar();
        } catch (IOException ex) {
            Logger.getLogger(CargandoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void Cambiar() throws IOException{
        
        if(pro.getP().getProgress() == 5){
            
            System.out.println("chaoo");
//            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentController.fxml"));
//        Scene scene = new Scene(root);
//        
//        Stage stage = (Stage)((Node)carg_bar).getClass().getWindow();
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.setAlwaysOnTop(true);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.centerOnScreen();
//        
//        stage.show();
        }
    }
    
}
