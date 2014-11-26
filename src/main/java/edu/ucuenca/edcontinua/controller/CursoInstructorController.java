package edu.ucuenca.edcontinua.controller;

import edu.ucuenca.edcontinua.entities.CursoInstructor;
import edu.ucuenca.edcontinua.controller.util.JsfUtil;
import edu.ucuenca.edcontinua.controller.util.JsfUtil.PersistAction;
import edu.ucuenca.edcontinua.farcade.CursoInstructorFacade;

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

@Named("cursoInstructorController")
@SessionScoped
public class CursoInstructorController implements Serializable {

    @EJB
    private edu.ucuenca.edcontinua.farcade.CursoInstructorFacade ejbFacade;
    private List<CursoInstructor> items = null;
    private CursoInstructor selected;

    public CursoInstructorController() {
    }

    public CursoInstructor getSelected() {
        return selected;
    }

    public void setSelected(CursoInstructor selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CursoInstructorFacade getFacade() {
        return ejbFacade;
    }

    public CursoInstructor prepareCreate() {
        selected = new CursoInstructor();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CursoInstructorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void create(CursoInstructor selected2, edu.ucuenca.edcontinua.farcade.CursoInstructorFacade ejb) {
        this.ejbFacade=ejb;
        this.selected=selected2;
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CursoInstructorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CursoInstructorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CursoInstructorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CursoInstructor> getItems() {
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

    public CursoInstructor getCursoInstructor(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CursoInstructor> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CursoInstructor> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CursoInstructor.class)
    public static class CursoInstructorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CursoInstructorController controller = (CursoInstructorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cursoInstructorController");
            return controller.getCursoInstructor(getKey(value));
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
            if (object instanceof CursoInstructor) {
                CursoInstructor o = (CursoInstructor) object;
                return getStringKey(o.getIdCursoInstructor());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CursoInstructor.class.getName()});
                return null;
            }
        }

    }

}
