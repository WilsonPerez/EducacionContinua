/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucuenca.controller.manager;

import edu.ucuenca.edcontinua.entities.Detalle;
import edu.ucuenca.edcontinua.entities.*;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author wilson
 */

public class CursoL implements Serializable {

    private String idCurso;
    private String nombre;
    private String descripccion;
    private String modulos;
    private Tipo idTipo;
    //private Collection<Instructor> instructorCollection;
    private Collection<Modulos> moduloCollection;
    private Collection<DirigidoA> dirigidoaCollection;
    private Collection<Detalle> detalleCollection;

    public CursoL() {
    }

    public CursoL(String idCurso) {
        this.idCurso = idCurso;
    }

    public CursoL(String idCurso, String nombre) {
        this.idCurso = idCurso;
        this.nombre = nombre;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
    }

    public Tipo getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Tipo idTipo) {
        this.idTipo = idTipo;
    }

    /*
     public Collection<Instructor> getCursoInstructorCollection() {
        return instructorCollection;
    }

    public void setCursoInstructorCollection(Collection<Instructor> cursoInstructorCollection) {
        this.instructorCollection = cursoInstructorCollection;
    }*/

    public Collection<Modulos> getModuloCollection() {
        return moduloCollection;
    }

    public void setModuloCollection(Collection<Modulos> moduloCollection) {
        this.moduloCollection = moduloCollection;
    }

    public Collection<DirigidoA> getCursoDirigidoaCollection() {
        return dirigidoaCollection;
    }

    public void setCursoDirigidoaCollection(Collection<DirigidoA> cursoDirigidoaCollection) {
        this.dirigidoaCollection = cursoDirigidoaCollection;
    }

    public Collection<Detalle> getDetalleCollection() {
        return detalleCollection;
    }

    public void setDetalleCollection(Collection<Detalle> detalleCollection) {
        this.detalleCollection = detalleCollection;
    }

    public String getModulos() {
        return modulos;
    }

    public void setModulos(String modulos) {
        this.modulos = modulos;
    }
    
}