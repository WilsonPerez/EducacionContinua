package edu.ucuenca.edcontinua.entities;

import edu.ucuenca.edcontinua.entities.Curso;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T10:54:28")
@StaticMetamodel(Tipo.class)
public class Tipo_ { 

    public static volatile CollectionAttribute<Tipo, Curso> cursoCollection;
    public static volatile SingularAttribute<Tipo, Integer> idTipo;
    public static volatile SingularAttribute<Tipo, String> nombre;

}