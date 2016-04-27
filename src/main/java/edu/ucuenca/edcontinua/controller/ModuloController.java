package edu.ucuenca.edcontinua.controller;

import edu.ucuenca.edcontinua.entities.Modulo;
import edu.ucuenca.edcontinua.controller.util.JsfUtil;
import edu.ucuenca.edcontinua.controller.util.JsfUtil.PersistAction;
import edu.ucuenca.edcontinua.entities.Curso;
import edu.ucuenca.edcontinua.entities.CursoInstructor;
import edu.ucuenca.edcontinua.entities.Instructor;
import edu.ucuenca.edcontinua.entities.ModuloInstructor;
import edu.ucuenca.edcontinua.farcade.ModuloFacade;
import idi.ucuenca.controller.util.GenericController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

@Named("moduloController")
@SessionScoped
public class ModuloController implements Serializable {

    @EJB
    private edu.ucuenca.edcontinua.farcade.ModuloFacade ejbFacade;
    private List<Modulo> items = new ArrayList<Modulo>();
    private List<Modulo> itemsFilter;
    private List<Modulo> itemsByCreate = new ArrayList<Modulo>();
    private Modulo selected;
    
    @EJB
    private edu.ucuenca.edcontinua.farcade.ModuloFacade ejbFacadeModulo;
    private List<Modulo> itemsWhere = new ArrayList<Modulo>();
    private Instructor instructor = new Instructor();
    
    
    boolean visibleCurso=false;
    
    
    @EJB
    private edu.ucuenca.edcontinua.farcade.InstructorFacade ejbFacadeInstructor;
    @EJB
    private edu.ucuenca.edcontinua.farcade.ModuloInstructorFacade ejbFacadeModuloInstructor;
    private DualListModel<Instructor> instructores;
    List<ModuloInstructor> intructoresModulo = new ArrayList<ModuloInstructor>();  //Lo q se tenia antes en DualListModel<Instructor>
    
    List<Instructor> intructoresAntesAsignados = new ArrayList<Instructor>();  //Lo q se tenia antes en DualListModel<Instructor>
    List<Instructor> intructoresAntesNoAsignados = new ArrayList<Instructor>();  //Lo q se tenia antes en DualListModel<Instructor>
        

    public ModuloController() {
        List<Instructor> instructores2 = new ArrayList<Instructor>();
        List<Instructor> instructores3 = new ArrayList<Instructor>();
        
        instructores= new DualListModel<Instructor>(instructores2, instructores3);
        selected = new Modulo();
        
        GenericController controller = new GenericController();
        //(new Series(), "c.studyFk.pk='"+idStudy+"'");
         items = controller.listarTodos(new Modulo());
         
        //if((ejbFacadeModulo!=null)==true)
            //items = ejbFacadeModulo.findWhere("SELECT distinct w FROM Modulo w WHERE w.idCurso.idCurso='"+selected.getIdCurso()+"'");
    }

    @PostConstruct
    public void init() {
       // items = ejbFacadeModulo.findAll();
        /*List<Modulo> citiesTarget = new ArrayList<Modulo>();
        themes= new DualListModel<Modulo>(moduls, citiesTarget);
        
        initDualList();*/
        initDualList();
    }
    
     /**
     *  Actualiza los datos del DualListMOdel relacionado al instructor.
     */
    public void EditInstructorDualList(){
        ModuloInstructor instanceModuloInstructor=new ModuloInstructor();
        instanceModuloInstructor.setIdModulo(selected);
        
        boolean bandera=true;
        //Comprobamos los eliminados
        
        for(int i=0; i<intructoresAntesAsignados.size(); i++){
            bandera=true;
            for(int j=0; j<instructores.getTarget().size(); j++){
                if(instructores.getTarget().get(j).getCi().compareTo(intructoresAntesAsignados.get(i).getCi())==0)
                    bandera=false;
            }
            if (bandera==true){
                instanceModuloInstructor.setIdInstructor(intructoresAntesAsignados.get(i));
                //findWhere("SELECT distinct w FROM Modulo w WHERE w.idCurso.idCurso='"+selected.getIdCurso()+"'");
                List<ModuloInstructor> findWhere = ejbFacadeModuloInstructor.findWhere("SELECT distinct w FROM ModuloInstructor w WHERE w.idModulo.idModulo='"+instanceModuloInstructor.getIdModulo().getIdModulo().toString()+"' and w.idInstructor.ci='"+instanceModuloInstructor.getIdInstructor().getCi()+"'");
                ejbFacadeModuloInstructor.remove(findWhere.get(0));
            }
        }
        
        //Comprobamos los agregados
        for(int i=0; i<intructoresAntesNoAsignados.size(); i++){
            bandera=true;
            for(int j=0; j<instructores.getSource().size(); j++){
                if(instructores.getSource().get(j).getCi().compareTo(intructoresAntesNoAsignados.get(i).getCi())==0)
                    bandera=false;
            }if (bandera==true){
                instanceModuloInstructor.setIdInstructor(intructoresAntesNoAsignados.get(i));
                ejbFacadeModuloInstructor.create(instanceModuloInstructor);
            }
        }
    }
    
    public void edit(){
        
        //ejbFacadeModulo.findWhere("SELECT distinct w FROM Modulo w WHERE w.idCurso.idCurso='"+selected.getIdCurso()+"'")
        //List<ModuloInstructor> findWhere = ejbFacadeInstructor.findWhere("SELECT distinct w FROM ModuloInstructor w WHERE w.idModulo.idModulo='"+selected.getIdModulo()+"'");
        List<ModuloInstructor> arrList = ejbFacadeModuloInstructor.findWhere("SELECT distinct w FROM ModuloInstructor w WHERE w.idModulo.idModulo='"+selected.getIdModulo()+"'");
        
        List<Instructor> instructores2 = new ArrayList<Instructor>();
        //List<ModuloInstructor> arrList=(List<ModuloInstructor>) selected.getModuloInstructorCollection();
        for(int i=0; i<arrList.size(); i++){
            instructores2.add(arrList.get(i).getIdInstructor());
        }
        
        //ejbFacadeModulo.findWhere("SELECT distinct w FROM Modulo w WHERE w.idCurso.idCurso='"+selected.getIdCurso()+"'");
        
        GenericController controller = new GenericController();
        //(new Series(), "c.studyFk.pk='"+idStudy+"'");
         //items = controller.listarTodos(new Modulo());
         
        List<Instructor> all=controller.listarTodos(new Instructor());
        List<Instructor> instructores3 = new ArrayList<Instructor>();
        boolean ban=true;
        
        for(int a=0; a<all.size(); a++){
            ban=true;
            for(int j=0; j<instructores2.size(); j++){
                if(all.get(a).getCi().compareTo(instructores2.get(j).getCi())==0)
                    ban=false;
            }
            if(ban==true)
                instructores3.add(all.get(a));
        }
        //List<Instructor> instructores3 = selected.getModuloInstructorCollection();
        
        instructores= new DualListModel<Instructor>(instructores3, instructores2);
        
        intructoresAntesAsignados=instructores2;
        intructoresAntesNoAsignados=instructores3;
    }
    public Modulo getSelected() {
        if (selected==null){
            selected = new Modulo();
        initializeEmbeddableKey();
        }
        return selected;
    }
    public List<Modulo> getItemsByCreate() {
        //if(itemsByCreate!=null)
             if (itemsByCreate==null){
            itemsByCreate=new ArrayList<Modulo>();
        }
        return itemsByCreate;
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
    }  
    
    public boolean isVisibleCurso() {
        return visibleCurso;
    }

    public void setVisibleCurso(boolean visibleCurso) {
        this.visibleCurso = visibleCurso;
    }

    public void setItemsByCreate(List<Modulo> itemsByCreate) {
        this.itemsByCreate = itemsByCreate;
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

    public Modulo prepareCreate(Curso curso) {
        selected = new Modulo();
        selected.setIdCurso(curso);
        //itemsByCreate.add(selected);
        this.visibleCurso=true;
        
        
        List<Instructor> instructores2 = new ArrayList<Instructor>();
        List<Instructor> instructores3 = new ArrayList<Instructor>();
        
        instructores= new DualListModel<Instructor>(instructores2, instructores3);
        selected = new Modulo();
        
        GenericController controller = new GenericController();
        //(new Series(), "c.studyFk.pk='"+idStudy+"'");
         items = controller.listarTodos(new Modulo());
         
         initDualList();
        return selected;
        
        
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
            items = ejbFacadeModulo.findAll();
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
                if (persistAction.toString().compareTo(PersistAction.CREATE.toString())==0) {

                    getFacade().create(selected);
                    GuardarInstructor();
                }else if(persistAction.toString().compareTo(PersistAction.UPDATE.toString())==0){
                //if (persistAction != PersistAction.DELETE) {
                    
                    getFacade().edit(selected);
                    EditInstructorDualList();
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
        selected=null;
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

    
    public void initDualList(){
       
        List<Instructor> instructores2 = ejbFacadeInstructor.findAll();
        List<Instructor> instructores3 = new ArrayList<Instructor>();
        
        instructores= new DualListModel<Instructor>(instructores2, instructores3);
    }
     public DualListModel<Instructor> getInstructores() {
        return instructores;
    }

    public void setInstructores(DualListModel<Instructor> instructores) {
        this.instructores = instructores;
    }
    /**
     Se coloca los datos al momento de Editar el Instructor
    */
    public void setDataInstructor(){
        List<Instructor> instructores2 = ejbFacadeInstructor.findAll();
        List<Instructor> instructores3 = new ArrayList<Instructor>();
        if(selected!=null){
            
            intructoresModulo = ejbFacadeModuloInstructor.findWhere("SELECT w FROM ModuloInstructor w WHERE w.idModulo.idModulo='"+selected.getIdModulo()+"'");  
            if(intructoresModulo.size()>0){
                for(int i=0; i<intructoresModulo.size(); i++){
                    List<Instructor> findWhere = ejbFacadeInstructor.findWhere("SELECT w FROM Instructor w WHERE w.ci='"+intructoresModulo.get(i).getIdInstructor().getCi()+"'");
                    instructores3.add(findWhere.get(0));
                }

                List<Instructor> instructoresAux = new ArrayList<Instructor>();
                boolean bandera=true;
                for(int i=0; i<instructores2.size(); i++){
                    bandera=true;
                    for(int j=0; j<intructoresModulo.size(); j++){
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
    
    public void GuardarInstructor(){
        for(int i=0; i<instructores.getTarget().size(); i++){
            GuardarInstructorCurso(instructores.getTarget().get(i).getCi());
        }
    }
    
    public void GuardarInstructorCurso(String ciInstructor){
        ModuloInstructor instanceModuloInstructor=new ModuloInstructor();
        instanceModuloInstructor.setIdModulo(selected);
        instanceModuloInstructor.setIdInstructor(ejbFacadeInstructor.findWhere("SELECT w FROM Instructor w WHERE w.ci='"+ciInstructor+"'").get(0));

        ModuloInstructorController moduloInstructor=new ModuloInstructorController();
        moduloInstructor.create(instanceModuloInstructor,ejbFacadeModuloInstructor);
    }
    
     /**
     *  Actualiza los datos del DualListMOdel relacionado al instructor
     */
    /*public void EditInstructorDualList(){
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
    }*/
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Modulo> getItemsFilter() {
        return itemsFilter;
    }

    public void setItemsFilter(List<Modulo> itemsFilter) {
        this.itemsFilter = itemsFilter;
    }
    
}
