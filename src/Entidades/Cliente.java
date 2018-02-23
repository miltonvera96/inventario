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
public class Cliente {
    
    //atributos perteneientes a cada cliente.   
    private SimpleStringProperty ruc;   // puede ser el RUC de una empresa o el numerode de una persona
    private SimpleStringProperty nombre;  //puede ser razon social de una empresa o nommbre de la persona
    private SimpleStringProperty telefono;
    private SimpleStringProperty direccion;
    private SimpleStringProperty ciudad;
    private SimpleStringProperty tipo;
    private SimpleStringProperty codigo;
    
    
    //contructor para Cliente
    public Cliente(String codigo,String RUC, String nombre, String telefono, String ciudad, String direccion, String tipo) {
        this.ruc = new SimpleStringProperty(RUC);
        this.nombre = new SimpleStringProperty(nombre);
        this.telefono = new SimpleStringProperty(telefono);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.direccion = new SimpleStringProperty(direccion);
        this.tipo = new SimpleStringProperty(tipo);
        this.codigo = new SimpleStringProperty(codigo);
    }

    
    //Metodos getters y settes de atributos definidoa anteriormente para Cliente
    
    //--------//
    // Getters//
    //--------//
    
    public String getRuc() {
        return ruc.get();
    }


    public String getNombre() {
        return nombre.get();
    }
    

    public String getTelefono() {
        return telefono.get();
    }
    

    public String getCiudad() {
        return ciudad.get();
    }


    public String getDireccion() {
        return direccion.get();
    }

    public String getTipo() {
        return tipo.get();
    }
    
    public String getCodigo() {
        return codigo.get();
    }

    
    //--------//
    // Setters//
    //--------//
    
    public void setRUC(String ruc) {
        this.ruc.set(ruc);
    }
    
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }
    
    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }
    
    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }
    
    public void setCodigo(String codigo){
        this.codigo.set(codigo);
    }
    
    public static Cliente buscarPorId(ObservableList<Cliente> lista, String codigo){
        
        for(Cliente c : lista){
            if( c.getCodigo() == codigo){
                return c;
            }
        }
        return null;
    }

}

