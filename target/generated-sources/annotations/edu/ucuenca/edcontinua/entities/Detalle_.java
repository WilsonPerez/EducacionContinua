package edu.ucuenca.edcontinua.entities;

import edu.ucuenca.edcontinua.entities.Curso;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T10:54:28")
@StaticMetamodel(Detalle.class)
public class Detalle_ { 

    public static volatile SingularAttribute<Detalle, String> numTelefono;
    public static volatile SingularAttribute<Detalle, Integer> numHoras;
    public static volatile SingularAttribute<Detalle, Double> precio;
    public static volatile SingularAttribute<Detalle, String> direccionCurso;
    public static volatile SingularAttribute<Detalle, Integer> idDetalle;
    public static volatile SingularAttribute<Detalle, Date> fechaInicio;
    public static volatile SingularAttribute<Detalle, String> objetivos;
    public static volatile SingularAttribute<Detalle, String> lugarInscripcion;
    public static volatile SingularAttribute<Detalle, Curso> idCurso;
    public static volatile SingularAttribute<Detalle, Date> fechaFin;
    public static volatile SingularAttribute<Detalle, Integer> numCupos;
    public static volatile SingularAttribute<Detalle, Date> fechaInscripcion;

}