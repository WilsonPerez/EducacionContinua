/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucuenca.controller.manager;

import edu.ucuenca.edcontinua.entities.Instructor;
import edu.ucuenca.edcontinua.entities.Tipo;
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

@FacesConverter(value = "tipoConverter")
@SessionScoped
public class TipoConverter implements Converter, Serializable{
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        // Convert ProjectDetail to its unique String representation.
        Tipo obj = (Tipo) value;
        String idAsString = String.valueOf(obj.getNombre());
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
        
        Tipo obj = new Tipo();//delegacionManager.findDelegacion(id);
        obj.setNombre(id2);
        
        return obj;
    }
}
