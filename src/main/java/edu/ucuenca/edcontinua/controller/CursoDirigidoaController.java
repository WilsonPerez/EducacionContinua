package edu.ucuenca.edcontinua.controller;

import edu.ucuenca.edcontinua.entities.CursoDirigidoa;
import edu.ucuenca.edcontinua.controller.util.JsfUtil;
import edu.ucuenca.edcontinua.controller.util.JsfUtil.PersistAction;
import edu.ucuenca.edcontinua.farcade.CursoDirigidoaFacade;

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

@Named("cursoDirigidoaController")
@SessionScoped
public class CursoDirigidoaController implements Serializable {

    @EJB
    private edu.ucuenca.edcontinua.farcade.CursoDirigidoaFacade ejbFacade;
    private List<CursoDirigidoa> items = null;
    private CursoDirigidoa selected;

    public CursoDirigidoaController() {
    }

    public CursoDirigidoa getSelected() {
        return selected;
    }

    public void setSelected(CursoDirigidoa selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CursoDirigidoaFacade getFacade() {
        return ejbFacade;
    }

    public CursoDirigidoa prepareCreate() {
        selected = new CursoDirigidoa();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CursoDirigidoaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void create(CursoDirigidoa selected2,edu.ucuenca.edcontinua.farcade.CursoDirigidoaFacade ejb) {
        this.ejbFacade=ejb;
        this.selected=selected2;
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CursoDirigidoaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CursoDirigidoaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CursoDirigidoaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CursoDirigidoa> getItems() {
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

    public CursoDirigidoa getCursoDirigidoa(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CursoDirigidoa> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CursoDirigidoa> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CursoDirigidoa.class)
    public static class CursoDirigidoaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CursoDirigidoaController controller = (CursoDirigidoaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cursoDirigidoaController");
            return controller.getCursoDirigidoa(getKey(value));
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
            if (object instanceof CursoDirigidoa) {
                CursoDirigidoa o = (CursoDirigidoa) object;
                return getStringKey(o.getIdCursoDirigidoa());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CursoDirigidoa.class.getName()});
                return null;
            }
        }

    }

}
