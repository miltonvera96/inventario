/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import com.mysql.jdbc.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class ConexionDB {
    
     private String url;
     private String user;
     private String password;
     private Connection connectionDB;
    
    
    public ConexionDB(){
        
        this.url = "jdbc:mysql://localhost:3306/distribuidora";   //url a donde se enuentra la base de datos distribuidora
        
        this.user = "administrador";
        this.password = "admin";
        
//        this.user = "empleados";
//        this.password = "empl";
        
    }
    
   
    //Establece una conexion con la base de datos y la retorna
    public Connection getConexion() throws ClassNotFoundException{        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
                connectionDB = DriverManager.getConnection(url , user ,password);

            //connectionDB = (Connection) DriverManager.getConnection(url, user, password);
            return connectionDB;
        }
        catch(SQLException e){
            
            System.out.println("No conectado");
        }
        
        return null;
    }
    
    public void CloseConnection()
     {
        try{
            if(connectionDB != null && !connectionDB.isClosed())
                connectionDB.close();
        }catch(SQLException ex)
        {
          //FXDialog.showMessageDialog("MySQL Error: " + ex.getMessage(), "Al cerrar la conexión", Message.INFORMATION);
        }
    }
    
        
       
}
