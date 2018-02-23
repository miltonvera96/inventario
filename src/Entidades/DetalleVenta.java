/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Usuario
 */
public class DetalleVenta {
    
    private SimpleStringProperty iddetalle;
    private SimpleStringProperty idproducto;
    private SimpleStringProperty descripcion;
    private SimpleFloatProperty precio;
    public SimpleIntegerProperty cantidad;
    private SimpleStringProperty idorden;

    public DetalleVenta(String iddetalle, String idproducto, String descripicon, Float precio, int cantidad,String idorden) {
        this.iddetalle = new SimpleStringProperty(iddetalle);
        this.idproducto = new SimpleStringProperty(idproducto);
        this.descripcion = new SimpleStringProperty(descripicon);
        this.precio = new SimpleFloatProperty(precio);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.idorden = new SimpleStringProperty(idorden);
    }

    public String getIddetalle() {
        return iddetalle.get();
    }

    public void setIddetalle(String iddetalle) {
        this.iddetalle.set(iddetalle);
    }

    public String getIdproducto() {
        return idproducto.get();
    }

    public void setIdproducto(String idproducto) {
        this.idproducto.set(idproducto);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripicion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public Float getPrecio() {
        return precio.get();
    }

    public void setPrecio(Float precio) {
        this.precio.set(precio);
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad.set(cantidad);
    }
    
    
    public String getIdorden() {
        return idorden.get();
    }

    public void setIdorden(String idorden) {
        this.idorden.set(idorden);
    }
    
}
