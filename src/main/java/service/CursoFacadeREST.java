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
    @Produces({"application/xml", "application/json"})
    public List<Curso> findAll() {
        return super.findAll();
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
