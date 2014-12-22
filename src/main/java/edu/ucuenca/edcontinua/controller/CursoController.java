package edu.ucuenca.edcontinua.controller;

import edu.ucuenca.edcontinua.entities.Curso;
import edu.ucuenca.edcontinua.controller.util.JsfUtil;
import edu.ucuenca.edcontinua.controller.util.JsfUtil.PersistAction;
import edu.ucuenca.edcontinua.entities.CursoDirigidoa;
import edu.ucuenca.edcontinua.entities.CursoInstructor;
import edu.ucuenca.edcontinua.entities.Detalle;
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
import javax.faces.event.AjaxBehaviorEvent;
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
    @EJB
    private edu.ucuenca.edcontinua.farcade.DetalleFacade ejbFacadeDetalle;
    
    //private Detalle selected;
    
    
    private DualListModel<Modulo> themes;
    private Modulo theme = new Modulo();
    
    private DualListModel<DirigidoA> dirigidosa;
    
    private DualListModel<DirigidoA> dirigidosaM;    //Dirigido A cuando sea a modificar
    
    private DirigidoA dirigidoa = new DirigidoA();
    
    private DualListModel<Instructor> instructores;
    private Instructor instructor = new Instructor();
    
    List<CursoInstructor> intructoresCurso = new ArrayList<CursoInstructor>();  //Lo q se tenia antes en DualListModel<Instructor>
    List<CursoDirigidoa> DirigidoaCurso = new ArrayList<CursoDirigidoa>();  //Lo q se tenia antes en DualListModel<CursoDirigidoa>
    
    Detalle detalleGuardar = new Detalle();

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
    
    public DualListModel<DirigidoA> getDirigidosaM() {
        return dirigidosaM;
    }

    public void setDirigidosaM(DualListModel<DirigidoA> dirigidosaM) {
        this.dirigidosaM = dirigidosaM;
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

    public void create(Detalle detalle) {
        detalleGuardar=detalle;
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
    }
    
    public List<Curso> getItemsInstructor(Curso selected) {
        return items;
    }

    /**
     Se ejecuta cuando se selecciona la opcion de modificar instructor
    */
    public void editInstructor(){
        setDataInstructor();
        setDataDirigidoa();
    }
    /**
     Se coloca los datos al momento de Editar el Instructor
    */
    public void setDataInstructor(){
        List<Instructor> instructores2 = ejbFacadeInstructor.findAll();
        List<Instructor> instructores3 = new ArrayList<Instructor>();
        if(selected!=null){
            
            intructoresCurso = ejbFacadeCursoInstructor.findWhere("SELECT w FROM CursoInstructor w WHERE w.idCurso.idCurso='"+selected.getIdCurso()+"'");  
            if(intructoresCurso.size()>0){
                for(int i=0; i<intructoresCurso.size(); i++){
                    List<Instructor> findWhere = ejbFacadeInstructor.findWhere("SELECT w FROM Instructor w WHERE w.ci='"+intructoresCurso.get(i).getIdInstructor().getCi()+"'");
                    instructores3.add(findWhere.get(0));
                }

                List<Instructor> instructoresAux = new ArrayList<Instructor>();
                boolean bandera=true;
                for(int i=0; i<instructores2.size(); i++){
                    bandera=true;
                    for(int j=0; j<intructoresCurso.size(); j++){
                        if(instructores2.get(i).getCi().compareTo(instructores3.get(j).getCi())==0)
                            bandera=false;
                    }
                    if(bandera==true)
                        instructoresAux.add(instructores2.get(i));
                }
                instructores2=instructoresAux;
            }
        }
        instructores= new DualListModel<Instructor>(instructores2, instructores3);
    }
    
    /**
     Se coloca los datos al momento de Editar el Dirigidoa
    */
    public void setDataDirigidoa(){
        List<DirigidoA> instructores2 = ejbFacadeDigido.findAll();
        List<DirigidoA> instructores3 = new ArrayList<DirigidoA>();
        if(selected!=null){
            
            DirigidoaCurso = ejbFacadeCursoDirigidoa.findWhere("SELECT w FROM CursoDirigidoa w WHERE w.idCurso.idCurso='"+selected.getIdCurso()+"'");  
            if(DirigidoaCurso.size()>0){
                for(int i=0; i<DirigidoaCurso.size(); i++){
                    List<DirigidoA> findWhere = ejbFacadeDigido.findWhere("SELECT w FROM DirigidoA w WHERE w.idDirigidoa='"+DirigidoaCurso.get(i).getIdDirigidoa().getIdDirigidoa()+"'");
                    instructores3.add(findWhere.get(0));
                }

                List<DirigidoA> instructoresAux = new ArrayList<DirigidoA>();
                boolean bandera=true;
                for(int i=0; i<instructores2.size(); i++){
                    bandera=true;
                    for(int j=0; j<DirigidoaCurso.size(); j++){
                        if(instructores2.get(i).getIdDirigidoa().compareTo(instructores3.get(j).getIdDirigidoa())==0)
                            bandera=false;
                    }
                    if(bandera==true)
                        instructoresAux.add(instructores2.get(i));
                }
                instructores2=instructoresAux;
            }
        }
        dirigidosa= new DualListModel<DirigidoA>(instructores2, instructores3);
    }
        
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction.toString().compareTo(PersistAction.CREATE.toString())==0) {
                    getFacade().edit(selected);
                    GuardarDetalle();
                    GuardarInstructor();
                    GuardarDirigidoa();
                    
                } else if(persistAction.toString().compareTo(PersistAction.UPDATE.toString())==0){
                    getFacade().edit(selected);
                    EditInstructorDualList();
                    EditDirigidosDualList();
                }else{
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
    }  
    
    public void onTransferInstructor(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Instructor) item).getNombre()).append("<br />");
        }
         
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  

    /*
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
    }*/
    
    public void GuardarInstructor(){
        for(int i=0; i<instructores.getTarget().size(); i++){
            GuardarInstructorCurso(instructores.getTarget().get(i).getCi());
        }
    }
    
    public void GuardarInstructorCurso(String ciInstructor){
        CursoInstructor instanceCursoInstructor=new CursoInstructor();
        instanceCursoInstructor.setIdCurso(selected);
        instanceCursoInstructor.setIdInstructor(ejbFacadeInstructor.findWhere("SELECT w FROM Instructor w WHERE w.ci='"+ciInstructor+"'").get(0));

        CursoInstructorController cursoInstructor=new CursoInstructorController();
        cursoInstructor.create(instanceCursoInstructor,ejbFacadeCursoInstructor);
    }
    
    /**
     *  Actualiza los datos del DualListMOdel relacionado al instructor
     */
    public void EditInstructorDualList(){
        CursoInstructor instanceCursoInstructor=new CursoInstructor();
        instanceCursoInstructor.setIdCurso(selected);
        
        Curso newCurso=new Curso();
        newCurso=selected;
        instanceCursoInstructor.setIdCurso(newCurso);
        
        boolean bandera=true;
        //Comprobamos los eliminados
        for(int i=0; i<intructoresCurso.size(); i++){
            bandera=true;
            for(int j=0; j<instructores.getTarget().size(); j++){
                if(instructores.getTarget().get(j).getCi().compareTo(intructoresCurso.get(i).getIdInstructor().getCi())==0)
                    bandera=false;
            }if (bandera==true)
                ejbFacadeCursoInstructor.remove(intructoresCurso.get(i));
        }
        
        //Comprobamos los agregados
        for(int i=0; i<instructores.getTarget().size(); i++){
            bandera=true;
            for(int j=0; j<intructoresCurso.size(); j++){
                if(instructores.getTarget().get(i).getCi().compareTo(intructoresCurso.get(j).getIdInstructor().getCi())==0)
                    bandera=false;
            }if (bandera==true)
                GuardarInstructorCurso(instructores.getTarget().get(i).getCi());
        }
    }
    
    /**
     *  Actualiza los datos del DualListMOdel relacionado a DirigidoA
     */
    public void EditDirigidosDualList(){
        //CursoDirigidoa instanceCursoDirigidoa=new CursoDirigidoa();
        CursoDirigidoa instanceCursoDirigidoa=new CursoDirigidoa();
        instanceCursoDirigidoa.setIdCurso(selected);
        
        boolean bandera=true;
        //Comprobamos los eliminados
        for(int i=0; i<DirigidoaCurso.size(); i++){
            bandera=true;
            for(int j=0; j<dirigidosa.getTarget().size(); j++){
                if(dirigidosa.getTarget().get(j).getIdDirigidoa().compareTo(DirigidoaCurso.get(i).getIdDirigidoa().getIdDirigidoa())==0)
                    bandera=false;
            }if (bandera==true)
                ejbFacadeCursoDirigidoa.remove(DirigidoaCurso.get(i));
        }
        
        //Comprobamos los agregados
        for(int i=0; i<dirigidosa.getTarget().size(); i++){
            bandera=true;
            for(int j=0; j<DirigidoaCurso.size(); j++){
                if(dirigidosa.getTarget().get(i).getIdDirigidoa().compareTo(DirigidoaCurso.get(j).getIdDirigidoa().getIdDirigidoa())==0)
                    bandera=false;
            }if (bandera==true)
                GuardarDirigidoaCurso(dirigidosa.getTarget().get(i));
        }
    }
    public void GuardarDirigidoa(){
        for(int i=0; i<dirigidosa.getTarget().size(); i++){
            GuardarDirigidoaCurso(dirigidosa.getTarget().get(i));
        }
    }
    
    public void GuardarDetalle(){
        detalleGuardar.setIdCurso(selected);
        DetalleController detallecontrol=new DetalleController();
        detallecontrol.create(detalleGuardar, ejbFacadeDetalle);
        //ejbFacadeDetalle.create(detalleGuardar);
    }
    
    public void GuardarDirigidoaCurso(DirigidoA dirigidoa){
        CursoDirigidoa instanceCursoDirigidoa=new CursoDirigidoa();
        instanceCursoDirigidoa.setIdCurso(selected);
        instanceCursoDirigidoa.setIdDirigidoa(dirigidoa);
        
        CursoDirigidoaController cursoDirigidoa=new CursoDirigidoaController();
        cursoDirigidoa.create(instanceCursoDirigidoa,ejbFacadeCursoDirigidoa);
    }
    
    public void addDetalle(AjaxBehaviorEvent event){
        int a =0;
        int aa =0;
    }
}
