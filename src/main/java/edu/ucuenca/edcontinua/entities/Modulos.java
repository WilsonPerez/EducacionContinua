/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucuenca.edcontinua.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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


public class Modulos implements Serializable {
   
    
   
    private Integer idModulo;
    private String nombre;
    private String descripcion;
    private String nombreInstructor;
    private String ciInstructor;
    private String apellidoInstructor;
    private String profesionInstructor;
    private String estudiosAdicionalesInstructor;
    private String tipoInstructor;
    
    public Modulos() {
    }

    public Modulos(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Modulos(Integer idModulo, String nombre) {
        this.idModulo = idModulo;
        this.nombre = nombre;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreInstructor() {
        return nombreInstructor;
    }

    public void setNombreInstructor(String nombreInstructor) {
        this.nombreInstructor = nombreInstructor;
    }

    public String getCiInstructor() {
        return ciInstructor;
    }

    public void setCiInstructor(String ciInstructor) {
        this.ciInstructor = ciInstructor;
    }

    public String getApellidoInstructor() {
        return apellidoInstructor;
    }

    public void setApellidoInstructor(String apellidoInstructor) {
        this.apellidoInstructor = apellidoInstructor;
    }

    public String getProfesionInstructor() {
        return profesionInstructor;
    }

    public void setProfesionInstructor(String profesionInstructor) {
        this.profesionInstructor = profesionInstructor;
    }

    public String getEstudiosAdicionalesInstructor() {
        return estudiosAdicionalesInstructor;
    }

    public void setEstudiosAdicionalesInstructor(String estudiosAdicionalesInstructor) {
        this.estudiosAdicionalesInstructor = estudiosAdicionalesInstructor;
    }

    public String getTipoInstructor() {
        return tipoInstructor;
    }

    public void setTipoInstructor(String tipoInstructor) {
        this.tipoInstructor = tipoInstructor;
    }

}
