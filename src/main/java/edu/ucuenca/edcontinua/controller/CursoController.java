package edu.ucuenca.edcontinua.controller;

import edu.ucuenca.edcontinua.entities.Curso;
import edu.ucuenca.edcontinua.controller.util.JsfUtil;
import edu.ucuenca.edcontinua.controller.util.JsfUtil.PersistAction;
import edu.ucuenca.edcontinua.entities.CursoDirigidoa;
import edu.ucuenca.edcontinua.entities.CursoInstructor;
import edu.ucuenca.edcontinua.entities.DirigidoA;
import edu.ucuenca.edcontinua.entities.Instructor;
import edu.ucuenca.edcontinua.entities.Modulo;
import edu.ucuenca.edcontinua.farcade.CursoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

@Named("cursoController")
@SessionScoped
public class CursoController implements Serializable {

    @EJB
    private edu.ucuenca.edcontinua.farcade.CursoFacade ejbFacade;
    private List<Curso> items = null;
    private Curso selected;

    @EJB
    private edu.ucuenca.edcontinua.farcade.InstructorFacade ejbFacadeInstructor;
    @EJB
    private edu.ucuenca.edcontinua.farcade.ModuloFacade ejbFacadeModulo;
    @EJB
    private edu.ucuenca.edcontinua.farcade.DirigidoAFacade ejbFacadeDigido;
    @EJB
    private edu.ucuenca.edcontinua.farcade.CursoInstructorFacade ejbFacadeCursoInstructor;
    @EJB
    private edu.ucuenca.edcontinua.farcade.CursoDirigidoaFacade ejbFacadeCursoDirigidoa;
    
    
    private DualListModel<Modulo> themes;
    private Modulo theme = new Modulo();
    
    private DualListModel<DirigidoA> dirigidosa;
    private DirigidoA dirigidoa = new DirigidoA();
    
    private DualListModel<Instructor> instructores;
    private Instructor instructor = new Instructor();

    public CursoController() {
        
    }
    @PostConstruct
    public void init() {
        List<Modulo> moduls = ejbFacadeModulo.findAll();
        List<Modulo> citiesTarget = new ArrayList<Modulo>();
        themes= new DualListModel<Modulo>(moduls, citiesTarget);
        
        List<DirigidoA> dirigidos = ejbFacadeDigido.findAll();
        List<DirigidoA> dirigidos2 = new ArrayList<DirigidoA>();
        dirigidosa= new DualListModel<DirigidoA>(dirigidos, dirigidos2);
        
        List<Instructor> instructores2 = ejbFacadeInstructor.findAll();
        List<Instructor> instructores3 = new ArrayList<Instructor>();
        instructores= new DualListModel<Instructor>(instructores2, instructores3);
    }

    public DualListModel<Modulo> getThemes() {
        return themes;
    }

    public DualListModel<Instructor> getInstructores() {
        return instructores;
    }

    public void setInstructores(DualListModel<Instructor> instructores) {
        this.instructores = instructores;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setThemes(DualListModel<Modulo> themes) {
        this.themes = themes;
    }

    public Curso getSelected() {
        return selected;
    }

    public void setSelected(Curso selected) {
        this.selected = selected;
    }

    public Modulo getTheme() {
        return theme;
    }

    public void setTheme(Modulo theme) {
        this.theme = theme;
    }

    public DualListModel<DirigidoA> getDirigidosa() {
        return dirigidosa;
    }

    public void setDirigidosa(DualListModel<DirigidoA> dirigidosa) {
        this.dirigidosa = dirigidosa;
    }

    public DirigidoA getDirigidoa() {
        return dirigidoa;
    }

    public void setDirigidoa(DirigidoA dirigidoa) {
        this.dirigidoa = dirigidoa;
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CursoFacade getFacade() {
        return ejbFacade;
    }

    public Curso prepareCreate() {
        selected = new Curso();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CursoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CursoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CursoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Curso> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }/*
    public List<Curso> getItemsInstructor() {
        List<CursoInstructor> findWhere = ejbFacadeInstructor.findWhere("SELECT distinct w FROM CursoInstructor w WHERE w.idCurso.idCurso='"+selected.getIdCurso()+"'");
        
        List<Instructor> itemsIns = new ArrayList<Instructor>();
        for(int i=0; i<findWhere.size(); i++){
            itemsIns.add(findWhere.get(i).getIdInstructor());
        }
        
            
        InstructorController instructorController=new InstructorController();
        instructorController.SetItemsWhere(itemsIns);
        return items;
    }*/
    public List<Curso> getItemsInstructor(Curso selected) {
       
        //SELECT distinct w FROM WsLibro w WHERE w.titulo like '%"+titulo+"%' order by w.mfn
        /*
        List<CursoInstructor> findWhere = ejbFacadeInstructor.findWhere("SELECT distinct w FROM CursoInstructor w WHERE w.idCurso.idCurso='"+selected.getIdCurso()+"'");
        
        List<Instructor> itemsIns = new ArrayList<Instructor>();
        for(int i=0; i<findWhere.size(); i++){
            itemsIns.add(findWhere.get(i).getIdInstructor());
        }
        
            
        InstructorController instructorController=new InstructorController();
        instructorController.SetItemsWhere(itemsIns);*/
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                    
                    GuardarInstructor();
                    GuardarDirigidoa();
                    
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

    public Curso getCurso(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Curso> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Curso> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Curso.class)
    public static class CursoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CursoController controller = (CursoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cursoController");
            return controller.getCurso(getKey(value));
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
            if (object instanceof Curso) {
                Curso o = (Curso) object;
                return getStringKey(o.getIdCurso());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Curso.class.getName()});
                return null;
            }
        }

    }
    
    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Modulo) item).getNombre()).append("<br />");
        }
         
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  

    public void GuardarInstructor(){
        CursoInstructor instanceCursoInstructor=new CursoInstructor();
        instanceCursoInstructor.setIdCurso(selected);
        
        Curso newCurso=new Curso();
        newCurso=selected;
        instanceCursoInstructor.setIdCurso(newCurso);
        
        for(int i=0; i<instructores.getTarget().size(); i++){
            Instructor instanceInstructor=new Instructor();
            instanceInstructor.setCi(instructores.getTarget().get(i).getCi());
            
            instanceCursoInstructor.setIdInstructor(ejbFacadeInstructor.findWhere("SELECT w FROM Instructor w WHERE w.ci='"+instructores.getTarget().get(0).getCi()+"'").get(0));

            CursoInstructorController cursoInstructor=new CursoInstructorController();
            cursoInstructor.create(instanceCursoInstructor,ejbFacadeCursoInstructor);
        }
    }
    
    public void GuardarDirigidoa(){
        CursoDirigidoa instanceCursoDirigidoa=new CursoDirigidoa();
        instanceCursoDirigidoa.setIdCurso(selected);
        
        for(int i=0; i<dirigidosa.getTarget().size(); i++){
            DirigidoA instanceDirigidoA=new DirigidoA();
            instanceDirigidoA.setIdDirigidoa(dirigidosa.getTarget().get(i).getIdDirigidoa());
            
            //instanceCursoInstructor.setIdInstructor(ejbFacadeInstructor.findWhere("SELECT w FROM Instructor w WHERE w.ci='"+instructores.getTarget().get(0).getCi()+"'").get(0));
            instanceCursoDirigidoa.setIdDirigidoa(instanceDirigidoA);
            //.setIdInstructor(ejbFacadeInstructor.findWhere("SELECT w FROM Instructor w WHERE w.ci='"+instructores.getTarget().get(0).getCi()+"'").get(0));
            
            CursoDirigidoaController cursoDirigidoa=new CursoDirigidoaController();
            cursoDirigidoa.create(instanceCursoDirigidoa,ejbFacadeCursoDirigidoa);
        }
    }
}
