package edu.ucuenca.edcontinua.entities;

import edu.ucuenca.edcontinua.entities.Curso;
import edu.ucuenca.edcontinua.entities.Instructor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T12:05:46")
@StaticMetamodel(CursoInstructor.class)
public class CursoInstructor_ { 

    public static volatile SingularAttribute<CursoInstructor, Curso> idCurso;
    public static volatile SingularAttribute<CursoInstructor, Integer> idCursoInstructor;
    public static volatile SingularAttribute<CursoInstructor, Instructor> idInstructor;

}