package edu.ucuenca.edcontinua.entities;

import edu.ucuenca.edcontinua.entities.Curso;
import edu.ucuenca.edcontinua.entities.ModuloInstructor;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T10:54:28")
@StaticMetamodel(Modulo.class)
public class Modulo_ { 

    public static volatile SingularAttribute<Modulo, String> descripcion;
    public static volatile CollectionAttribute<Modulo, ModuloInstructor> moduloInstructorCollection;
    public static volatile SingularAttribute<Modulo, Curso> idCurso;
    public static volatile SingularAttribute<Modulo, Integer> idModulo;
    public static volatile SingularAttribute<Modulo, String> nombre;

}