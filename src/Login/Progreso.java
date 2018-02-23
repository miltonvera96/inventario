/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import javafx.collections.ObservableMap;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Administrador
 */
public class Progreso extends Thread {
    
    @FXML public ProgressBar p;
    Task copyWorker;
    Thread hilo;
    int i;
    
    public Progreso(ProgressBar progreso){
        
        super();
        this.p = progreso;
        p.setProgress(0);
        copyWorker = createWorker();

        p.progressProperty().unbind();
        p.progressProperty().bind(copyWorker.progressProperty());
        hilo = new Thread(copyWorker);
    }
    
     public void run(){
        System.out.println("holaa");
        hilo.start();
        
        if(i== 8){
            Stage stage = new Stage();
            try{
                Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
                Scene scene = new Scene(root);
        
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }catch(Exception e){
            
            }
        }
        
    }
    
    
    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (i = 0; i < 10; i++) {
                    Thread.sleep(500);
                    //updateMessage("2000 milliseconds");
                    updateProgress(i + 1, 10);
                    //p.setProgress(i);
                }
                return true;
            }
        };
    }

    public ProgressBar getP() {
        return p;
    }

    public void setP(ProgressBar p) {
        this.p = p;
    }
    
    
}
