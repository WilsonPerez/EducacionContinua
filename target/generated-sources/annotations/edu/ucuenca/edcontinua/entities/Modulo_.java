package edu.ucuenca.edcontinua.entities;

import edu.ucuenca.edcontinua.entities.Curso;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T12:05:46")
@StaticMetamodel(Modulo.class)
public class Modulo_ { 

    public static volatile SingularAttribute<Modulo, String> nombre;
    public static volatile SingularAttribute<Modulo, Integer> idModulo;
    public static volatile SingularAttribute<Modulo, Curso> idCurso;
    public static volatile SingularAttribute<Modulo, String> descripcion;

}