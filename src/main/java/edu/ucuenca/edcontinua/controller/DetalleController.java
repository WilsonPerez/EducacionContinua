package edu.ucuenca.edcontinua.controller;

import edu.ucuenca.edcontinua.entities.Detalle;
import edu.ucuenca.edcontinua.controller.util.JsfUtil;
import edu.ucuenca.edcontinua.controller.util.JsfUtil.PersistAction;
import edu.ucuenca.edcontinua.entities.Curso;
import edu.ucuenca.edcontinua.entities.DirigidoA;
import edu.ucuenca.edcontinua.entities.Instructor;
import edu.ucuenca.edcontinua.farcade.DetalleFacade;

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
import org.primefaces.model.DualListModel;

@Named("detalleController")
@SessionScoped
public class DetalleController implements Serializable {

    @EJB
    private edu.ucuenca.edcontinua.farcade.DetalleFacade ejbFacade;
    @EJB
    private edu.ucuenca.edcontinua.farcade.DetalleFacade ejbFacadeDetalle;
    
    private List<Detalle> items = null;
    private List<Detalle> itemsFilter;
    private List<Detalle> itemsWhere = null;
    private Detalle selected;
    private Detalle selectedNull;
    Curso cursoSelect=null;
    
    boolean createDetalle=false;
    boolean visibleCurso=false;
    
    
    public DetalleController() {
    }

    public Detalle getSelected() {
        if(selected==null){
            selected=new Detalle();
            if(createDetalle==true)
                selected.setIdCurso(cursoSelect);
        }
        return selected;
    }

    public Detalle getSelectedNull() {
     
            selectedNull=new Detalle();
        selected=selectedNull;
        return selectedNull;
    }

    public void setSelectedNull(Detalle selectedNull) {
        this.selectedNull = selectedNull;
    }
    

    public boolean isVisibleCurso() {
        return visibleCurso;
    }

    public void setVisibleCurso(boolean visibleCurso) {
        this.visibleCurso = visibleCurso;
    }

    public void SetItemsWhere(Curso selected) {
       
        List<Detalle> findWhere = ejbFacadeDetalle.findWhere("SELECT distinct w FROM Detalle w WHERE w.idCurso.idCurso='"+selected.getIdCurso()+"'");
        
        List<Detalle> itemsIns = new ArrayList<Detalle>();
        for(int i=0; i<findWhere.size(); i++){
            itemsIns.add(findWhere.get(i));
        }
        itemsWhere=itemsIns;
    }

    public List<Detalle> getItemsWhere() {

        return itemsWhere;
    }
    
    public void setSelected(Detalle selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DetalleFacade getFacade() {
        return ejbFacade;
    }

    public Detalle prepareCreate() {
        selected = new Detalle();
        initializeEmbeddableKey();
        this.visibleCurso=false;
        return selected;
    }
    public Detalle prepareCreate(Curso curso) {
        this.cursoSelect=curso;
        this.visibleCurso=true;
        createDetalle=true;
        selected = new Detalle();
        selected.setIdCurso(curso);
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Detalle detalleGuardar, edu.ucuenca.edcontinua.farcade.DetalleFacade ejb) {
        this.ejbFacade=ejb;
        selected=detalleGuardar;
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DetalleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DetalleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetalleUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DetalleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Detalle> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction== PersistAction.DELETE) {
                    getFacade().remove(selected);
                }if (persistAction== PersistAction.CREATE) {
                    getFacade().create(selected);
                }if (persistAction== PersistAction.UPDATE){
                    getFacade().edit(selected);
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

    public Detalle getDetalle(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Detalle> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Detalle> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Detalle.class)
    public static class DetalleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleController controller = (DetalleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleController");
            return controller.getDetalle(getKey(value));
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
            if (object instanceof Detalle) {
                Detalle o = (Detalle) object;
                return getStringKey(o.getIdDetalle());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Detalle.class.getName()});
                return null;
            }
        }

    }

    public List<Detalle> getItemsFilter() {
        return itemsFilter;
    }

    public void setItemsFilter(List<Detalle> itemsFilter) {
        this.itemsFilter = itemsFilter;
    }
    
}
