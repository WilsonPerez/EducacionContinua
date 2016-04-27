/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucuenca.edcontinua.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wilson
 */
@Entity
@Table(name = "detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalle.findAll", query = "SELECT d FROM Detalle d"),
    @NamedQuery(name = "Detalle.findByIdDetalle", query = "SELECT d FROM Detalle d WHERE d.idDetalle = :idDetalle"),
    @NamedQuery(name = "Detalle.findByObjetivos", query = "SELECT d FROM Detalle d WHERE d.objetivos = :objetivos"),
    @NamedQuery(name = "Detalle.findByFechaInscripcion", query = "SELECT d FROM Detalle d WHERE d.fechaInscripcion = :fechaInscripcion"),
    @NamedQuery(name = "Detalle.findByNumHoras", query = "SELECT d FROM Detalle d WHERE d.numHoras = :numHoras"),
    @NamedQuery(name = "Detalle.findByLugarInscripcion", query = "SELECT d FROM Detalle d WHERE d.lugarInscripcion = :lugarInscripcion"),
    @NamedQuery(name = "Detalle.findByDireccionCurso", query = "SELECT d FROM Detalle d WHERE d.direccionCurso = :direccionCurso"),
    @NamedQuery(name = "Detalle.findByNumTelefono", query = "SELECT d FROM Detalle d WHERE d.numTelefono = :numTelefono"),
    @NamedQuery(name = "Detalle.findByPrecio", query = "SELECT d FROM Detalle d WHERE d.precio = :precio"),
    @NamedQuery(name = "Detalle.findByNumCupos", query = "SELECT d FROM Detalle d WHERE d.numCupos = :numCupos"),
    @NamedQuery(name = "Detalle.findByFechaInicio", query = "SELECT d FROM Detalle d WHERE d.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Detalle.findByFechaFin", query = "SELECT d FROM Detalle d WHERE d.fechaFin = :fechaFin")})
public class Detalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle")
    private Integer idDetalle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "objetivos")
    private String objetivos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;
    @Column(name = "num_horas")
    private Integer numHoras;
    @Size(max = 100)
    @Column(name = "lugar_inscripcion")
    private String lugarInscripcion;
    @Size(max = 100)
    @Column(name = "direccion_curso")
    private String direccionCurso;
    @Size(max = 15)
    @Column(name = "num_telefono")
    private String numTelefono;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "num_cupos")
    private Integer numCupos;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne
    private Curso idCurso;

    public Detalle() {
    }

    public Detalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Detalle(Integer idDetalle, String objetivos, Date fechaInscripcion) {
        this.idDetalle = idDetalle;
        this.objetivos = objetivos;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Integer getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(Integer numHoras) {
        this.numHoras = numHoras;
    }

    public String getLugarInscripcion() {
        return lugarInscripcion;
    }

    public void setLugarInscripcion(String lugarInscripcion) {
        this.lugarInscripcion = lugarInscripcion;
    }

    public String getDireccionCurso() {
        return direccionCurso;
    }

    public void setDireccionCurso(String direccionCurso) {
        this.direccionCurso = direccionCurso;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getNumCupos() {
        return numCupos;
    }

    public void setNumCupos(Integer numCupos) {
        this.numCupos = numCupos;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalle)) {
            return false;
        }
        Detalle other = (Detalle) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.ucuenca.edcontinua.entities.Detalle[ idDetalle=" + idDetalle + " ]";
    }
    
}
