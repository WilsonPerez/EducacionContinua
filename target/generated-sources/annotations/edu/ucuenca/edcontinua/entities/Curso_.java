package edu.ucuenca.edcontinua.entities;

import edu.ucuenca.edcontinua.entities.CursoDirigidoa;
import edu.ucuenca.edcontinua.entities.Detalle;
import edu.ucuenca.edcontinua.entities.Modulo;
import edu.ucuenca.edcontinua.entities.Tipo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T10:54:28")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile CollectionAttribute<Curso, Modulo> moduloCollection;
    public static volatile CollectionAttribute<Curso, Detalle> detalleCollection;
    public static volatile CollectionAttribute<Curso, CursoDirigidoa> cursoDirigidoaCollection;
    public static volatile SingularAttribute<Curso, String> idCurso;
    public static volatile SingularAttribute<Curso, String> descripccion;
    public static volatile SingularAttribute<Curso, Tipo> idTipo;
    public static volatile SingularAttribute<Curso, String> nombre;

}