/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucuenca.controller.manager;

import edu.ucuenca.edcontinua.entities.Instructor;
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

@FacesConverter(value = "instructorConverter")
@SessionScoped
public class InstructorConverter implements Converter, Serializable{
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        // Convert ProjectDetail to its unique String representation.
        Instructor obj = (Instructor) value;
        String idAsString = String.valueOf(obj.getCi());
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
        
        Instructor obj = new Instructor();//delegacionManager.findDelegacion(id);
        obj.setCi(id2);
        
        return obj;
    }
}
