/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucuenca.controller.manager;

import edu.ucuenca.edcontinua.entities.Modulo;
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

@FacesConverter(value = "moduloConverter")
@SessionScoped
public class ModuloConverter implements Converter, Serializable{
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        // Convert ProjectDetail to its unique String representation.
        Modulo obj = (Modulo) value;
        String idAsString = String.valueOf(obj.getIdModulo());
        return idAsString;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // Convert unique String representation of ProjectDetail back to ProjectDetail object.
        //Long id;
        int id2;
        try {
            //id = Long.valueOf(value);
            id2= Integer.valueOf(value);
        } catch(Exception e) {
            return null;
        }
        
        Modulo obj = new Modulo();//delegacionManager.findDelegacion(id);
        obj.setIdModulo(id2);
        
        return obj;
    }
}
