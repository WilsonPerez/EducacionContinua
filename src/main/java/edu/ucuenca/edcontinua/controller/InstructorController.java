package edu.ucuenca.edcontinua.controller;

import edu.ucuenca.edcontinua.entities.Instructor;
import edu.ucuenca.edcontinua.controller.util.JsfUtil;
import edu.ucuenca.edcontinua.controller.util.JsfUtil.PersistAction;
import edu.ucuenca.edcontinua.entities.Curso;
import edu.ucuenca.edcontinua.entities.CursoInstructor;
import edu.ucuenca.edcontinua.entities.Modulo;
import edu.ucuenca.edcontinua.entities.ModuloInstructor;
import edu.ucuenca.edcontinua.farcade.InstructorFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("instructorController")
@SessionScoped
public class InstructorController implements Serializable {

    @EJB
    private edu.ucuenca.edcontinua.farcade.InstructorFacade ejbFacade;
    @EJB
    private edu.ucuenca.edcontinua.farcade.ModuloInstructorFacade ejbFacadeInstructor;
    private List<Instructor> items = null;
    private List<Instructor> itemsFilter;
    private List<Instructor> itemsWhere = null;
    private Instructor selected;

    public InstructorController() {
        selected = new Instructor();
    }

    public Instructor getSelected() {
        return selected;
    }

    public void setSelected(Instructor selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InstructorFacade getFacade() {
        return ejbFacade;
    }

    public Instructor prepareCreate() {
        selected = new Instructor();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("InstructorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("InstructorUpdated"));
    }

    public void SetItemsWhere(Modulo selected) {
       
        List<ModuloInstructor> findWhere = ejbFacadeInstructor.findWhere("SELECT distinct w FROM ModuloInstructor w WHERE w.idModulo.idModulo='"+selected.getIdModulo()+"'");
        
        List<Instructor> itemsIns = new ArrayList<Instructor>();
        for(int i=0; i<findWhere.size(); i++){
            itemsIns.add(findWhere.get(i).getIdInstructor());
        }
        itemsWhere=itemsIns;
    }

    public List<Instructor> getItemsWhere() {

        return itemsWhere;
    }
    
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("InstructorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Instructor> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Instructor getInstructor(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Instructor> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Instructor> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Instructor.class)
    public static class InstructorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InstructorController controller = (InstructorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "instructorController");
            return controller.getInstructor(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Instructor) {
                Instructor o = (Instructor) object;
                return getStringKey(o.getCi());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Instructor.class.getName()});
                return null;
            }
        }

    }

    public List<Instructor> getItemsFilter() {
        return itemsFilter;
    }

    public void setItemsFilter(List<Instructor> itemsFilter) {
        this.itemsFilter = itemsFilter;
    }
    
}
