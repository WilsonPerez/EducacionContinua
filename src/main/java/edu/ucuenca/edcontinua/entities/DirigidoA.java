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
@Table(name = "dirigido_a")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DirigidoA.findAll", query = "SELECT d FROM DirigidoA d"),
    @NamedQuery(name = "DirigidoA.findByIdDirigidoa", query = "SELECT d FROM DirigidoA d WHERE d.idDirigidoa = :idDirigidoa"),
    @NamedQuery(name = "DirigidoA.findByNombre", query = "SELECT d FROM DirigidoA d WHERE d.nombre = :nombre")})
public class DirigidoA implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id_dirigidoa")
    private String idDirigidoa;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDirigidoa")
    private Collection<CursoDirigidoa> cursoDirigidoaCollection;

    public DirigidoA() {
    }

    public DirigidoA(String idDirigidoa) {
        this.idDirigidoa = idDirigidoa;
    }

    public String getIdDirigidoa() {
        return idDirigidoa;
    }

    public void setIdDirigidoa(String idDirigidoa) {
        this.idDirigidoa = idDirigidoa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<CursoDirigidoa> getCursoDirigidoaCollection() {
        return cursoDirigidoaCollection;
    }

    public void setCursoDirigidoaCollection(Collection<CursoDirigidoa> cursoDirigidoaCollection) {
        this.cursoDirigidoaCollection = cursoDirigidoaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDirigidoa != null ? idDirigidoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DirigidoA)) {
            return false;
        }
        DirigidoA other = (DirigidoA) object;
        if ((this.idDirigidoa == null && other.idDirigidoa != null) || (this.idDirigidoa != null && !this.idDirigidoa.equals(other.idDirigidoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.ucuenca.edcontinua.entities.DirigidoA[ idDirigidoa=" + idDirigidoa + " ]";
    }
    
}
