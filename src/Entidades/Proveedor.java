/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Usuario
 */
public class Proveedor {
    
    //Atributos respectivos a cada Proveedor
    private SimpleStringProperty ruc;
    private SimpleStringProperty descripcion;
    private SimpleStringProperty ciudad;
    private SimpleStringProperty estado;
    private SimpleStringProperty direccion;

    public Proveedor(String ruc, String descripcion, String ciudad, String estado, String direccion) {
        this.ruc = new SimpleStringProperty(ruc);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.estado = new SimpleStringProperty(estado);
        this.direccion = new SimpleStringProperty(direccion);
    }

    
    //Metodos getters y setters para los atributos antes definidos para Proveedor    
    
    //--------//
    // Getters//
    //--------//
    
    public String getRuc() {
        return ruc.get();
    }


    public String getDescripcion() {
        return descripcion.get();
    }
    

    public String getCiudad() {
        return ciudad.get();
    }


    public String getEstado() {
        return estado.get();
    }
    
    public String getDireccion() {
        return direccion.get();
    }

    
    //--------//
    // Setters//
    //--------//
    
    public void setRuc(String ruc) {
        this.ruc.set(ruc);
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    
    public void setTelefono(String telefono) {
        this.ciudad.set(telefono);
    }
    
    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }
    
    public void setEstado(String estado) {
        this.estado.set(estado);
    }
    
    public static Proveedor buscarPorId(ObservableList<Proveedor> lista, String ruc){
        
        for(Proveedor p : lista){
            if( p.getRuc().equalsIgnoreCase(ruc)){
                return p;
            }
        }
        return null;
    }
}
