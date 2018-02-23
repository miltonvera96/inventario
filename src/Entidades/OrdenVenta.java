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
public class OrdenVenta {
    
    private SimpleStringProperty idorden;
    private SimpleStringProperty fecha;
    private SimpleStringProperty idCliente;
    private SimpleStringProperty idEmpleado;
    private SimpleFloatProperty total;

    public OrdenVenta(String idorden, String fecha, String idcliente, String idempleado, Float total) {
        this.idorden = new SimpleStringProperty(idorden);
        this.fecha = new SimpleStringProperty(fecha);
        this.idCliente = new SimpleStringProperty(idcliente);
        this.idEmpleado = new SimpleStringProperty(idempleado);
        this.total = new SimpleFloatProperty(total);
    }

    public String getIdorden() {
        return idorden.get();
    }

    public void setIdorden(String idorden) {
        this.idorden.set(idorden);
    }

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public String getIdCliente() {
        return idCliente.get();
    }

    public void setIdCliente(String idCliente) {
        this.idCliente.set(idCliente);
    }


    public String getIdEmpleado() {
        return idEmpleado.get();
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado.set(idEmpleado);
    }

    public Float getTotal() {
        return total.get();
    }

    public void setTotal(Float Total) {
        this.total.set(Total);
    }
   
}
