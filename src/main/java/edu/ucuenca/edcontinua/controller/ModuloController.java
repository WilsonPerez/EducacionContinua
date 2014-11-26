package edu.ucuenca.edcontinua.controller;

import edu.ucuenca.edcontinua.entities.Modulo;
import edu.ucuenca.edcontinua.controller.util.JsfUtil;
import edu.ucuenca.edcontinua.controller.util.JsfUtil.PersistAction;
import edu.ucuenca.edcontinua.entities.Curso;
import edu.ucuenca.edcontinua.farcade.ModuloFacade;

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

@Named("moduloController")
@SessionScoped
public class ModuloController implements Serializable {

    @EJB
    private edu.ucuenca.edcontinua.farcade.ModuloFacade ejbFacade;
    private List<Modulo> items = null;
    private Modulo selected;
    
    @EJB
    private edu.ucuenca.edcontinua.farcade.ModuloFacade ejbFacadeModulo;
    
    private List<Modulo> itemsWhere = null;
    private Modulo modulo;

    public ModuloController() {
    }

    public Modulo getSelected() {
        return selected;
    }

    public void setSelected(Modulo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ModuloFacade getFacade() {
        return ejbFacade;
    }

    public Modulo prepareCreate() {
        selected = new Modulo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ModuloCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ModuloUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ModuloDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Modulo> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void SetItemsWhere(Curso selected) {
       
        List<Modulo> findWhere = ejbFacadeModulo.findWhere("SELECT distinct w FROM Modulo w WHERE w.idCurso.idCurso='"+selected.getIdCurso()+"'");
        
        List<Modulo> itemsIns = new ArrayList<Modulo>();
        for(int i=0; i<findWhere.size(); i++){
            itemsIns.add(findWhere.get(i));
        }
        itemsWhere=itemsIns;
    }

    public List<Modulo> getItemsWhere() {

        return itemsWhere;
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

    public Modulo getModulo(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Modulo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Modulo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Modulo.class)
    public static class ModuloControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ModuloController controller = (ModuloController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "moduloController");
            return controller.getModulo(getKey(value));
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
            if (object instanceof Modulo) {
                Modulo o = (Modulo) object;
                return getStringKey(o.getIdModulo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Modulo.class.getName()});
                return null;
            }
        }

    }

}
