package edu.ucuenca.edcontinua.entities;

import edu.ucuenca.edcontinua.entities.CursoInstructor;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-26T18:03:13")
@StaticMetamodel(Instructor.class)
public class Instructor_ { 

    public static volatile SingularAttribute<Instructor, String> nombre;
    public static volatile SingularAttribute<Instructor, String> apellido;
    public static volatile SingularAttribute<Instructor, String> profesion;
    public static volatile SingularAttribute<Instructor, String> tipo;
    public static volatile SingularAttribute<Instructor, String> ci;
    public static volatile CollectionAttribute<Instructor, CursoInstructor> cursoInstructorCollection;
    public static volatile SingularAttribute<Instructor, String> estudiosAdicionales;

}