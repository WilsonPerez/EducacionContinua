/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import edu.ucuenca.controller.manager.CursoL;
import edu.ucuenca.edcontinua.entities.Curso;
import edu.ucuenca.edcontinua.entities.CursoDirigidoa;
import edu.ucuenca.edcontinua.entities.CursoInstructor;
import edu.ucuenca.edcontinua.entities.Detalle;
import edu.ucuenca.edcontinua.entities.DirigidoA;
import edu.ucuenca.edcontinua.entities.Instructor;
import edu.ucuenca.edcontinua.entities.Modulo;
import edu.ucuenca.edcontinua.entities.Tipo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author wilson
 */
@Stateless
@Path("edu.ucuenca.edcontinua.entities.curso")
public class CursoFacadeREST extends AbstractFacade<Curso> {
    @PersistenceContext(unitName = "edu.ucuenca_mavenproject2_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @EJB
    private edu.ucuenca.edcontinua.farcade.DetalleFacade ejbFacadeDetalle;


    public CursoFacadeREST() {
        super(Curso.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Curso entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Curso entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public CursoL find(@PathParam("id") String id) {
    
        CursoL cursoR=new CursoL();
        Curso curso = super.find(id);
        
        cursoR.setNombre(curso.getNombre());
        cursoR.setDescripccion(curso.getDescripccion());
        cursoR.setIdCurso(curso.getIdCurso());
        
        Tipo idTipo = curso.getIdTipo();
        idTipo.setCursoCollection(null);
        cursoR.setIdTipo(idTipo);
        
        List<Modulo> moduloCollection = (List)curso.getModuloCollection();
        for (int i=0; i<moduloCollection.size(); i++){
            moduloCollection.get(i).setIdCurso(null);
        }
        cursoR.setModuloCollection(moduloCollection);
        
        List<Detalle> detalleCollection = (List)curso.getDetalleCollection();
        for (int i=0; i<detalleCollection.size(); i++){
            detalleCollection.get(i).setIdCurso(null);
        }
        cursoR.setDetalleCollection(detalleCollection);
        
        List<CursoInstructor> cursoInstructorCollection = (List)curso.getCursoInstructorCollection();
        List<Instructor> instructores=new ArrayList<Instructor>();
        for (int i=0; i<cursoInstructorCollection.size(); i++){
            Instructor idInstructor = cursoInstructorCollection.get(i).getIdInstructor();
            idInstructor.setCursoInstructorCollection(null);
            
            instructores.add(idInstructor);
        }
        cursoR.setCursoInstructorCollection(instructores);
        
        List<CursoDirigidoa> cursoDirigidoaCollection = (List)curso.getCursoDirigidoaCollection();
        List<DirigidoA> dirigidosa=new ArrayList<DirigidoA>();
        for (int i=0; i<cursoDirigidoaCollection.size(); i++){
            DirigidoA dirigidoa=cursoDirigidoaCollection.get(i).getIdDirigidoa();
            dirigidoa.setCursoDirigidoaCollection(null);
            dirigidosa.add(dirigidoa);
        }
        cursoR.setCursoDirigidoaCollection(dirigidosa);
        
        return cursoR;
    }
    
    @GET
    @Override
    @Produces({"application/json"})
    public List<CursoL> findAllRange() {
        //group by educacion_continua.detalle.id_curso
        List<Detalle> detalles = ejbFacadeDetalle.findWhere("SELECT w FROM Detalle w Order by w.fechaInscripcion");
        List<Curso> cursos = new ArrayList<Curso>();
        List<CursoL> cursosl = new ArrayList<CursoL>();
        
        for (int i=0; i<detalles.size(); i++){
            List<Detalle> get =new ArrayList<Detalle>();
            get.add(detalles.get(i));
            
            Curso cursoaux = new Curso();
            cursoaux.setCursoDirigidoaCollection(detalles.get(i).getIdCurso().getCursoDirigidoaCollection());
            cursoaux.setCursoInstructorCollection(detalles.get(i).getIdCurso().getCursoInstructorCollection());
            cursoaux.setDescripccion(detalles.get(i).getIdCurso().getDescripccion());
            cursoaux.setIdCurso(detalles.get(i).getIdCurso().getIdCurso());
            cursoaux.setIdTipo(detalles.get(i).getIdCurso().getIdTipo());
            cursoaux.setModuloCollection(detalles.get(i).getIdCurso().getModuloCollection());
            cursoaux.setNombre(detalles.get(i).getIdCurso().getNombre());
            cursoaux.setDetalleCollection(get);
            
            cursos.add(cursoaux);
        }
        
        for (int i=0; i<cursos.size(); i++){
            CursoL cursoR=new CursoL();
            //CursoL cursoR=cursos.get(i);
            cursoR.setNombre(cursos.get(i).getNombre());
            cursoR.setDescripccion(cursos.get(i).getDescripccion());
            cursoR.setIdCurso(cursos.get(i).getIdCurso());

            Tipo idTipo = cursos.get(i).getIdTipo();
            idTipo.setCursoCollection(null);
            cursoR.setIdTipo(idTipo);

            List<Modulo> moduloCollection = (List)cursos.get(i).getModuloCollection();
            List<Modulo> moduloCollection2 =new ArrayList<Modulo>();
            //cursoR.setModuloCollection(moduloCollection);
            
            moduloCollection = (List)cursos.get(i).getModuloCollection();
            for (int j=0; j<moduloCollection.size(); j++){
                Modulo modulo=new Modulo();
                modulo.setDescripcion(moduloCollection.get(j).getDescripcion());
                modulo.setIdModulo(moduloCollection.get(j).getIdModulo());
                modulo.setNombre(moduloCollection.get(j).getNombre());
                
                moduloCollection2.add(modulo);
                //moduloCollection.get(j).setIdCurso(null);
            }
            cursoR.setModuloCollection(moduloCollection2);

            List<Detalle> detalleCollection = (List)cursos.get(i).getDetalleCollection();
            List<Detalle> detalleCollection2 = new ArrayList<Detalle>();
            for (int j=0; j<detalleCollection.size(); j++){
                Detalle detalleC=new Detalle();
                detalleC.setDireccionCurso(detalleCollection.get(j).getDireccionCurso());
                detalleC.setFechaInscripcion(detalleCollection.get(j).getFechaInscripcion());
                detalleC.setIdDetalle(detalleCollection.get(j).getIdDetalle());
                detalleC.setLugarInscripcion(detalleCollection.get(j).getLugarInscripcion());
                detalleC.setNumCupos(detalleCollection.get(j).getNumCupos());
                detalleC.setNumHoras(detalleCollection.get(j).getNumHoras());
                detalleC.setNumTelefono(detalleCollection.get(j).getNumTelefono());
                detalleC.setObjetivos(detalleCollection.get(j).getObjetivos());
                detalleC.setPrecio(detalleCollection.get(j).getPrecio());
                
                detalleCollection2.add(detalleC);
            }
            cursoR.setDetalleCollection(detalleCollection2);

            List<CursoInstructor> cursoInstructorCollection = (List)cursos.get(i).getCursoInstructorCollection();
            List<Instructor> instructores=new ArrayList<Instructor>();
            for (int j=0; j<cursoInstructorCollection.size(); j++){
                Instructor idInstructor = cursoInstructorCollection.get(j).getIdInstructor();
                idInstructor.setCursoInstructorCollection(null);

                instructores.add(idInstructor);
            }
            cursoR.setCursoInstructorCollection(instructores);

            List<CursoDirigidoa> cursoDirigidoaCollection = (List)cursos.get(i).getCursoDirigidoaCollection();
            List<DirigidoA> dirigidosa=new ArrayList<DirigidoA>();
            for (int j=0; j<cursoDirigidoaCollection.size(); j++){
                DirigidoA dirigidoa=cursoDirigidoaCollection.get(j).getIdDirigidoa();
                dirigidoa.setCursoDirigidoaCollection(null);
                dirigidosa.add(dirigidoa);
            }
            cursoR.setCursoDirigidoaCollection(dirigidosa);

            cursosl.add(cursoR);
        }
        //return super.findAll();
        return cursosl;
    }
    
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Curso> findAll() {
        CursoL cursoR=new CursoL();
        
        //List<Curso> detalles = super.findWhere("SELECT w FROM Detalle w Order by w.fechaInscripcion");
        
        List<Detalle> detalles =ejbFacadeDetalle.findWhere("SELECT w FROM Detalle w Order by w.fechaInscripcion");
        
        List<Curso> cursos = new ArrayList<Curso>();
        for (int i=0; i<detalles.size(); i++){
            //List<Detalle> detalleCollection = (List)detalles.get(i).getIdCurso();
            detalles.get(i).getIdCurso();
            //detalleCollection.get(i).getIdCurso();
            //cursos.add(detalleCollection.get(0).getIdCurso());
        }
        //List<Detalle> detalleCollection = (List)detalles.get(0).getDetalleCollection();
        //detalleCollection.get(0).getIdCurso();
        
        Curso curso = super.find("1");
        cursoR.setNombre(curso.getNombre());
        cursoR.setDescripccion(curso.getDescripccion());
        cursoR.setIdCurso(curso.getIdCurso());
        
        Tipo idTipo = curso.getIdTipo();
        idTipo.setCursoCollection(null);
        cursoR.setIdTipo(idTipo);
        
        List<Modulo> moduloCollection = (List)curso.getModuloCollection();
        for (int i=0; i<moduloCollection.size(); i++){
            moduloCollection.get(i).setIdCurso(null);
        }
        cursoR.setModuloCollection(moduloCollection);
        
        List<Detalle> detalleCollection = (List)curso.getDetalleCollection();
        for (int i=0; i<detalleCollection.size(); i++){
            detalleCollection.get(i).setIdCurso(null);
        }
        cursoR.setDetalleCollection(detalleCollection);
        
        List<CursoInstructor> cursoInstructorCollection = (List)curso.getCursoInstructorCollection();
        List<Instructor> instructores=new ArrayList<Instructor>();
        for (int i=0; i<cursoInstructorCollection.size(); i++){
            Instructor idInstructor = cursoInstructorCollection.get(i).getIdInstructor();
            idInstructor.setCursoInstructorCollection(null);
            
            instructores.add(idInstructor);
        }
        cursoR.setCursoInstructorCollection(instructores);
        
        List<CursoDirigidoa> cursoDirigidoaCollection = (List)curso.getCursoDirigidoaCollection();
        List<DirigidoA> dirigidosa=new ArrayList<DirigidoA>();
        for (int i=0; i<cursoDirigidoaCollection.size(); i++){
            DirigidoA dirigidoa=cursoDirigidoaCollection.get(i).getIdDirigidoa();
            dirigidoa.setCursoDirigidoaCollection(null);
            dirigidosa.add(dirigidoa);
        }
        cursoR.setCursoDirigidoaCollection(dirigidosa);
        
        
        //return super.findAll();
        return null;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Curso> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
