/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucuenca.controller.manager;

import edu.ucuenca.edcontinua.entities.DirigidoA;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author wilson
 */

@FacesConverter(value = "dirigidoaConverter")
@SessionScoped
public class DirigidoaConverter implements Converter, Serializable{
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        // Convert ProjectDetail to its unique String representation.
        DirigidoA obj = (DirigidoA) value;
        String idAsString = String.valueOf(obj.getIdDirigidoa());
        return idAsString;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // Convert unique String representation of ProjectDetail back to ProjectDetail object.
        //Long id;
        String id2;
        try {
            //id = Long.valueOf(value);
            id2= value;
        } catch(Exception e) {
            return null;
        }
        
        DirigidoA obj = new DirigidoA();//delegacionManager.findDelegacion(id);
        obj.setIdDirigidoa(id2);
        
        return obj;
    }
}
