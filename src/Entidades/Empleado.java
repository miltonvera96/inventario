/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Usuario
 */
public class Empleado {
    
    //Atributos respectivos al Empleado
    private SimpleStringProperty idEmpleado;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellido;
    private SimpleStringProperty direccion;
    private SimpleStringProperty estado_Activo;
    private SimpleFloatProperty sueldo;
    private SimpleStringProperty cargo;
    private SimpleIntegerProperty administrador;
    private SimpleStringProperty usuario;
    private SimpleStringProperty password;
//
    //Constructor Empleado
    public Empleado(String cedula, String nombre, String apellido, String direccion, String estado,int administrador, float sueldo, String cargo,String usuario,String clave) {
        this.idEmpleado = new SimpleStringProperty(cedula);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.direccion = new SimpleStringProperty(direccion);
        this.estado_Activo = new SimpleStringProperty(estado);
        this.administrador = new SimpleIntegerProperty(administrador);
        this.sueldo = new SimpleFloatProperty(sueldo);
        this.cargo = new SimpleStringProperty(cargo);
        this.usuario = new SimpleStringProperty(usuario);
        this.password = new SimpleStringProperty(clave);
    }
    
    //--------//
    // Getters//
    //--------//
    
    public String getIdEmpleado() {
        return idEmpleado.get();
    }

    public String getNombre() {
        return nombre.get();
    }


    public String getApellido() {
        return apellido.get();
    }


    public String getDireccion() {
        return direccion.get();
    }

    public String getEstado_Activo() {
        return estado_Activo.get();
    }


    public Float getSueldo() {
        return sueldo.get();
    }


    public String getCargo() {
        return cargo.get();
    }

    public int getAdministrador() {
        return administrador.get();
    }

    public String getUsuario() {
        return usuario.get();
    }

    

    public String getPassword() {
        return password.get();
    }

    
    
    //--------//
    // Setters//
    //--------//
    
    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }
    
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    public void setCedula(String cedula) {
        this.idEmpleado.set(cedula);
    }
    
    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }
    
    public void setSueldo(float sueldo) {
        this.sueldo.set(sueldo);
    }
    
    public void setCargo(String cargo) {
        this.cargo.set(cargo);
    }

    public void setEstado(String estado) {
        this.estado_Activo.set(estado);
    }
    
    public void setAdministrador(int administrador) {
        this.administrador.set(administrador);
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }
    
    public void setPassword(String password) {
        this.password.set(password);
    }
    
    
    public static Empleado buscarPorId(ObservableList<Empleado> lista, String id){
        
        for(Empleado e : lista){
            if(e.getIdEmpleado().equals(id)){
                return e;
            }
        }
        return null;
    }
}
