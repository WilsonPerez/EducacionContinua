/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucuenca.edcontinua.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wilson
 */
@Entity
@Table(name = "curso_instructor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoInstructor.findAll", query = "SELECT c FROM CursoInstructor c"),
    @NamedQuery(name = "CursoInstructor.findByIdCursoInstructor", query = "SELECT c FROM CursoInstructor c WHERE c.idCursoInstructor = :idCursoInstructor")})
public class CursoInstructor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso_instructor")
    private Integer idCursoInstructor;
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private Curso idCurso;
    @JoinColumn(name = "id_instructor", referencedColumnName = "ci")
    @ManyToOne(optional = false)
    private Instructor idInstructor;

    public CursoInstructor() {
    }

    public CursoInstructor(Integer idCursoInstructor) {
        this.idCursoInstructor = idCursoInstructor;
    }

    public Integer getIdCursoInstructor() {
        return idCursoInstructor;
    }

    public void setIdCursoInstructor(Integer idCursoInstructor) {
        this.idCursoInstructor = idCursoInstructor;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public Instructor getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Instructor idInstructor) {
        this.idInstructor = idInstructor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoInstructor != null ? idCursoInstructor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoInstructor)) {
            return false;
        }
        CursoInstructor other = (CursoInstructor) object;
        if ((this.idCursoInstructor == null && other.idCursoInstructor != null) || (this.idCursoInstructor != null && !this.idCursoInstructor.equals(other.idCursoInstructor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.ucuenca.edcontinua.entities.CursoInstructor[ idCursoInstructor=" + idCursoInstructor + " ]";
    }
    
}
