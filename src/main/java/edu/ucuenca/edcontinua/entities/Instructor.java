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
import javax.persistence.Id;
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
@Entity
@Table(name = "instructor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instructor.findAll", query = "SELECT i FROM Instructor i"),
    @NamedQuery(name = "Instructor.findByCi", query = "SELECT i FROM Instructor i WHERE i.ci = :ci"),
    @NamedQuery(name = "Instructor.findByNombre", query = "SELECT i FROM Instructor i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Instructor.findByApellido", query = "SELECT i FROM Instructor i WHERE i.apellido = :apellido"),
    @NamedQuery(name = "Instructor.findByProfesion", query = "SELECT i FROM Instructor i WHERE i.profesion = :profesion"),
    @NamedQuery(name = "Instructor.findByEstudiosAdicionales", query = "SELECT i FROM Instructor i WHERE i.estudiosAdicionales = :estudiosAdicionales"),
    @NamedQuery(name = "Instructor.findByTipo", query = "SELECT i FROM Instructor i WHERE i.tipo = :tipo")})
public class Instructor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ci")
    private String ci;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 50)
    @Column(name = "profesion")
    private String profesion;
    @Size(max = 100)
    @Column(name = "estudios_adicionales")
    private String estudiosAdicionales;
    @Size(max = 100)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstructor")
    private Collection<CursoInstructor> cursoInstructorCollection;

    public Instructor() {
    }

    public Instructor(String ci) {
        this.ci = ci;
    }

    public Instructor(String ci, String nombre, String apellido) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEstudiosAdicionales() {
        return estudiosAdicionales;
    }

    public void setEstudiosAdicionales(String estudiosAdicionales) {
        this.estudiosAdicionales = estudiosAdicionales;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<CursoInstructor> getCursoInstructorCollection() {
        return cursoInstructorCollection;
    }

    public void setCursoInstructorCollection(Collection<CursoInstructor> cursoInstructorCollection) {
        this.cursoInstructorCollection = cursoInstructorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ci != null ? ci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instructor)) {
            return false;
        }
        Instructor other = (Instructor) object;
        if ((this.ci == null && other.ci != null) || (this.ci != null && !this.ci.equals(other.ci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.ucuenca.edcontinua.entities.Instructor[ ci=" + ci + " ]";
    }
    
}
