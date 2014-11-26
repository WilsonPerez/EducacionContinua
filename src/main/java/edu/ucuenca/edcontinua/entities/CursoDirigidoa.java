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
@Table(name = "curso_dirigidoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoDirigidoa.findAll", query = "SELECT c FROM CursoDirigidoa c"),
    @NamedQuery(name = "CursoDirigidoa.findByIdCursoDirigidoa", query = "SELECT c FROM CursoDirigidoa c WHERE c.idCursoDirigidoa = :idCursoDirigidoa")})
public class CursoDirigidoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso_dirigidoa")
    private Integer idCursoDirigidoa;
    @JoinColumn(name = "id_dirigidoa", referencedColumnName = "id_dirigidoa")
    @ManyToOne(optional = false)
    private DirigidoA idDirigidoa;
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private Curso idCurso;

    public CursoDirigidoa() {
    }

    public CursoDirigidoa(Integer idCursoDirigidoa) {
        this.idCursoDirigidoa = idCursoDirigidoa;
    }

    public Integer getIdCursoDirigidoa() {
        return idCursoDirigidoa;
    }

    public void setIdCursoDirigidoa(Integer idCursoDirigidoa) {
        this.idCursoDirigidoa = idCursoDirigidoa;
    }

    public DirigidoA getIdDirigidoa() {
        return idDirigidoa;
    }

    public void setIdDirigidoa(DirigidoA idDirigidoa) {
        this.idDirigidoa = idDirigidoa;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoDirigidoa != null ? idCursoDirigidoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoDirigidoa)) {
            return false;
        }
        CursoDirigidoa other = (CursoDirigidoa) object;
        if ((this.idCursoDirigidoa == null && other.idCursoDirigidoa != null) || (this.idCursoDirigidoa != null && !this.idCursoDirigidoa.equals(other.idCursoDirigidoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.ucuenca.edcontinua.entities.CursoDirigidoa[ idCursoDirigidoa=" + idCursoDirigidoa + " ]";
    }
    
}
