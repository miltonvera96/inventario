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
public class Producto {

    private SimpleStringProperty codigo;
    private SimpleStringProperty descripcion;
    private SimpleStringProperty tipo;
    private SimpleStringProperty linea;
    private SimpleStringProperty categoria;
    private SimpleFloatProperty precio;
    private SimpleIntegerProperty cantidad;
    private SimpleStringProperty bodega;

    public Producto(String codigo, String descripcion, String tipo, String linea, String categoria, Float precio, Integer cantidad, String bodega) {
        this.codigo = new SimpleStringProperty(codigo);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.tipo = new SimpleStringProperty(tipo);
        this.linea = new SimpleStringProperty(linea);
        this.categoria = new SimpleStringProperty(categoria);
        this.precio = new SimpleFloatProperty(precio);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.bodega = new SimpleStringProperty(bodega);
    }

    

    //Metodos getters y setters para los atributos antes definidos para Producto
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

    public String getLinea() {
        return linea.get();
    }

    public void setLinea(String linea) {
        this.linea.set(linea);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public String getCategoria() {
        return categoria.get();
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }

    public float getPrecio() {
        return precio.get();
    }

    public void setPrecio(float precio) {
        this.precio.set(precio);
    }

    public String getBodega() {
        return bodega.get();
    }

    public void setBodega(String bodega) {
        this.bodega.set(bodega);
    }
    
    
    public int getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public static Producto buscarPorId(ObservableList<Producto> lista, String id){
        
        for(Producto p : lista){
            if(p.getCodigo().equals(id)){
                return p;
            }
        }
        return null;
    }
    
}
