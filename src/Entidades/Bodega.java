/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Usuario
 */
public class Bodega {
    
    //atributos repsectivos de cada bodega
    private SimpleStringProperty codigo;
    private SimpleStringProperty tipo;
    private SimpleStringProperty direccion;

    
    //construtor de Bodega
    public Bodega(String codigo, String tipo, String direccion) {
        this.codigo = new SimpleStringProperty(codigo);
        this.tipo = new SimpleStringProperty(tipo);
        this.direccion = new SimpleStringProperty(direccion);
    }

    //Metodos getters y setters para los atributos definidos anteriormente
    public String getCodigo() {
        return codigo.get();
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String Direccion) {
        this.direccion.set(Direccion);
    }
    
    
    
}
