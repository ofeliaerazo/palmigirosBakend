package com.palmigiros.jpa.entities;

import com.palmigiros.jpa.entities.Ciudades;
import com.palmigiros.jpa.entities.Giros;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-01T13:56:51")
@StaticMetamodel(Clientes.class)
public class Clientes_ { 

    public static volatile SingularAttribute<Clientes, String> apellidos;
    public static volatile ListAttribute<Clientes, Giros> girosList1;
    public static volatile SingularAttribute<Clientes, Integer> id;
    public static volatile SingularAttribute<Clientes, String> numeroDocumento;
    public static volatile SingularAttribute<Clientes, String> telefono;
    public static volatile SingularAttribute<Clientes, Ciudades> idCiudad;
    public static volatile SingularAttribute<Clientes, String> nombres;
    public static volatile ListAttribute<Clientes, Giros> girosList;

}