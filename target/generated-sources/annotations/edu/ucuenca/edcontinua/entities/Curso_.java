package edu.ucuenca.edcontinua.entities;

import edu.ucuenca.edcontinua.entities.CursoDirigidoa;
import edu.ucuenca.edcontinua.entities.CursoInstructor;
import edu.ucuenca.edcontinua.entities.Detalle;
import edu.ucuenca.edcontinua.entities.Modulo;
import edu.ucuenca.edcontinua.entities.Tipo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T12:05:46")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile SingularAttribute<Curso, String> nombre;
    public static volatile SingularAttribute<Curso, String> idCurso;
    public static volatile CollectionAttribute<Curso, Modulo> moduloCollection;
    public static volatile CollectionAttribute<Curso, CursoInstructor> cursoInstructorCollection;
    public static volatile CollectionAttribute<Curso, CursoDirigidoa> cursoDirigidoaCollection;
    public static volatile SingularAttribute<Curso, Tipo> idTipo;
    public static volatile SingularAttribute<Curso, String> descripccion;
    public static volatile CollectionAttribute<Curso, Detalle> detalleCollection;

}