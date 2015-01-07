package edu.ucuenca.edcontinua.entities;

import edu.ucuenca.edcontinua.entities.Curso;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-07T15:28:54")
@StaticMetamodel(Tipo.class)
public class Tipo_ { 

    public static volatile SingularAttribute<Tipo, String> nombre;
    public static volatile CollectionAttribute<Tipo, Curso> cursoCollection;
    public static volatile SingularAttribute<Tipo, Integer> idTipo;

}