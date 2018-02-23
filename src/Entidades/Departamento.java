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
public class Departamento {
    
    //Atributos respectivos a a Departamento
    private SimpleIntegerProperty codigo;
    private SimpleStringProperty nombre;
    private SimpleStringProperty nomina;

    //Consructor para Departamento
    public Departamento(int codigo, String nombre, String nomina) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.nomina = new SimpleStringProperty(nomina);
    }

    //Metodos getteres y setters para los atributos antes definidos para Departamento
    public int getCodigo() {
        return codigo.get();
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getNomina() {
        return nomina.get();
    }

    public void setNomina(String nomina) {
        this.nomina.set(nomina);
    }
    
    public static Departamento buscarPorId(ObservableList<Departamento> lista, int codigo){
        
        for(Departamento d : lista){
            if( d.getCodigo() == codigo){
                return d;
            }
        }
        return null;
    }
    
}
