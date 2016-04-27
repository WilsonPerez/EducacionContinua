package edu.ucuenca.edcontinua.controller;

import edu.ucuenca.edcontinua.entities.CursoInstructor;
import edu.ucuenca.edcontinua.controller.util.JsfUtil;
import edu.ucuenca.edcontinua.controller.util.JsfUtil.PersistAction;
import edu.ucuenca.edcontinua.entities.ModuloInstructor;
import edu.ucuenca.edcontinua.farcade.CursoInstructorFacade;
import edu.ucuenca.edcontinua.farcade.ModuloInstructorFacade;

import java.io.Serializable;
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

@Named("moduloInstructorController")
@SessionScoped
public class ModuloInstructorController implements Serializable {

    @EJB
    private edu.ucuenca.edcontinua.farcade.ModuloInstructorFacade ejbFacade;
    private List<ModuloInstructor> items = null;
    private ModuloInstructor selected;

    public ModuloInstructorController() {
    }

    public ModuloInstructor getSelected() {
        return selected;
    }

    public void setSelected(ModuloInstructor selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ModuloInstructorFacade getFacade() {
        return ejbFacade;
    }

    public ModuloInstructor prepareCreate() {
        selected = new ModuloInstructor();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ModuloInstructorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void create(ModuloInstructor selected2, edu.ucuenca.edcontinua.farcade.ModuloInstructorFacade ejb) {
        this.ejbFacade=ejb;
        this.selected=selected2;
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ModuloInstructorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ModuloInstructorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ModuloInstructorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ModuloInstructor> getItems() {
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

    public ModuloInstructor getModuloInstructor(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ModuloInstructor> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ModuloInstructor> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ModuloInstructor.class)
    public static class ModuloInstructorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ModuloInstructorController controller = (ModuloInstructorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ModuloInstructorController");
            return controller.getModuloInstructor(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ModuloInstructor) {
                ModuloInstructor o = (ModuloInstructor) object;
                return getStringKey(o.getIdCursoInstructor());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CursoInstructor.class.getName()});
                return null;
            }
        }

    }

}
