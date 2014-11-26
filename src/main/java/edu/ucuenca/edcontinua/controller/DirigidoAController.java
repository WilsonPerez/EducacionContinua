package edu.ucuenca.edcontinua.controller;

import edu.ucuenca.edcontinua.entities.DirigidoA;
import edu.ucuenca.edcontinua.controller.util.JsfUtil;
import edu.ucuenca.edcontinua.controller.util.JsfUtil.PersistAction;
import edu.ucuenca.edcontinua.entities.Curso;
import edu.ucuenca.edcontinua.entities.CursoDirigidoa;
import edu.ucuenca.edcontinua.farcade.DirigidoAFacade;

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

@Named("dirigidoAController")
@SessionScoped
public class DirigidoAController implements Serializable {

    @EJB
    private edu.ucuenca.edcontinua.farcade.DirigidoAFacade ejbFacade;
     @EJB
    private edu.ucuenca.edcontinua.farcade.CursoDirigidoaFacade ejbFacadeCDirigido;
    private List<DirigidoA> items = null;
    private List<DirigidoA> itemsWhere = null;
    private DirigidoA selected;

    public DirigidoAController() {
    }

    public DirigidoA getSelected() {
        return selected;
    }

    public void SetItemsWhere(Curso selected) {
       
        List<CursoDirigidoa> findWhere = ejbFacadeCDirigido.findWhere("SELECT distinct w FROM CursoDirigidoa w WHERE w.idCurso.idCurso='"+selected.getIdCurso()+"'");
        
        List<DirigidoA> itemsIns = new ArrayList<DirigidoA>();
        for(int i=0; i<findWhere.size(); i++){
            itemsIns.add(findWhere.get(i).getIdDirigidoa());
        }
        itemsWhere=itemsIns;
    }

    public List<DirigidoA> getItemsWhere() {
        return itemsWhere;
    }
    
    public void setSelected(DirigidoA selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DirigidoAFacade getFacade() {
        return ejbFacade;
    }

    public DirigidoA prepareCreate() {
        selected = new DirigidoA();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DirigidoACreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DirigidoAUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DirigidoADeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DirigidoA> getItems() {
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

    public DirigidoA getDirigidoA(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<DirigidoA> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DirigidoA> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DirigidoA.class)
    public static class DirigidoAControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DirigidoAController controller = (DirigidoAController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "dirigidoAController");
            return controller.getDirigidoA(getKey(value));
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
            if (object instanceof DirigidoA) {
                DirigidoA o = (DirigidoA) object;
                return getStringKey(o.getIdDirigidoa());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DirigidoA.class.getName()});
                return null;
            }
        }

    }

}
