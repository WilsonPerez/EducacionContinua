/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucuenca.controller.manager;

import edu.ucuenca.edcontinua.entities.*;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wilson
 */

public class CursoL implements Serializable {

    private String idCurso;
    private String nombre;
    private String descripccion;
    private Tipo idTipo;
    private Collection<CursoInstructor> cursoInstructorCollection;
    private Collection<Modulo> moduloCollection;
    private Collection<CursoDirigidoa> cursoDirigidoaCollection;
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

     public Collection<CursoInstructor> getCursoInstructorCollection() {
        return cursoInstructorCollection;
    }

    public void setCursoInstructorCollection(Collection<CursoInstructor> cursoInstructorCollection) {
        this.cursoInstructorCollection = cursoInstructorCollection;
    }

    public Collection<Modulo> getModuloCollection() {
        return moduloCollection;
    }

    public void setModuloCollection(Collection<Modulo> moduloCollection) {
        this.moduloCollection = moduloCollection;
    }

    public Collection<CursoDirigidoa> getCursoDirigidoaCollection() {
        return cursoDirigidoaCollection;
    }

    public void setCursoDirigidoaCollection(Collection<CursoDirigidoa> cursoDirigidoaCollection) {
        this.cursoDirigidoaCollection = cursoDirigidoaCollection;
    }

    public Collection<Detalle> getDetalleCollection() {
        return detalleCollection;
    }

    public void setDetalleCollection(Collection<Detalle> detalleCollection) {
        this.detalleCollection = detalleCollection;
    }
}
