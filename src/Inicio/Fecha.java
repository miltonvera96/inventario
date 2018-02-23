/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Usuario
 */
public class Fecha {
    
    private GregorianCalendar gcalendar;
    private String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", 
         "Oct", "Nov", "Dec"};
    private int anio, mes, dia;
    
    public Fecha(){
        
        gcalendar = new GregorianCalendar();
        mes = gcalendar.get(Calendar.MONTH) + 1;
        anio = gcalendar.get(Calendar.YEAR);
        dia = gcalendar.get(Calendar.DATE);
        
    }
    
    public String getFechaLetra(){
        
        String fecha_final;
        
        String mesLetra = months[mes];
        
        fecha_final = Integer.toString(dia) + "-" + mesLetra + "-" + Integer.toString(anio);
        
        return fecha_final;
        
    }
    
    public String getFechaNumero(){
        
        String fecha_final;
        
        String mesNum = Integer.toString(mes);
        String diaNum = Integer.toString(dia);;
        
        if(mes < 10 ){
             mesNum = "0" + Integer.toString(mes);           
        }
        if(dia < 10 ){
             diaNum = "0" + Integer.toString(dia);           
        }
        
        fecha_final = Integer.toString(anio) + "-" + mesNum + "-" + diaNum;
        return fecha_final;
    }
    
    
    public String getHora(){
        
        String hora_final;
        
        int hora = gcalendar.get(Calendar.HOUR);
        int minuto = gcalendar.get(Calendar.MINUTE);
        int segundo = gcalendar.get(Calendar.SECOND);
        
         hora_final = Integer.toString(hora) + ":" + Integer.toString(minuto) + ":" + Integer.toString(segundo);
        
        return hora_final;
        
        
    }
    
    
}
