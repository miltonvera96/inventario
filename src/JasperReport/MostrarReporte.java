/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JasperReport;

import BaseDatos.ConexionDB;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Usuario
 */
public class MostrarReporte extends JFrame{
    
    ConexionDB con = new ConexionDB();
    PreparedStatement pst;
    ResultSet rs;
    String output = "/Users/Usuario/Desktop/Aldair/2017 1S/ProyectoBD_Files/";
    
    
    public void showReport(String file) throws JRException, ClassNotFoundException{
        
//            JasperDesign jasperDesign = JRXmlLoader.load("/Users/Usuario/Desktop/Aldair/2016 2S/NetBeans/ProtectoBDII/src/JasperReport/factura.jrxml");
//            String query = "select * from proveedores;";
//            JRDesignQuery jrquery = new JRDesignQuery();
//            jrquery.setText(query);
//            jasperDesign.setQuery(jrquery);
//            
//            HashMap<String, Object> map = new HashMap<>();
//            map.put("query", "select * from proveedores");

            //JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        
            JasperReport jasperReport = JasperCompileManager.compileReport("/Users/Usuario/Desktop/Aldair/2016 2S/NetBeans/ProtectoBDII/src/JasperReport/" + file);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, null, con.getConexion());
            JRViewer viewer = new JRViewer(print);
            
            viewer.setOpaque(true);
            viewer.setVisible(true);
            
            this.add(viewer);
            this.setSize(900,500);
            this.setVisible(true);
        
       
    } 
}
