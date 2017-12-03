package com.palmigiros.jpa.entities;

import com.palmigiros.jpa.entities.Clientes;
import com.palmigiros.jpa.entities.Departamentos;
import com.palmigiros.jpa.entities.Giros;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-01T13:56:51")
@StaticMetamodel(Ciudades.class)
public class Ciudades_ { 

    public static volatile SingularAttribute<Ciudades, Departamentos> idDepartamento;
    public static volatile ListAttribute<Ciudades, Giros> girosListEmisor;
    public static volatile ListAttribute<Ciudades, Clientes> clientesList;
    public static volatile ListAttribute<Ciudades, Giros> girosListReceptor;
    public static volatile SingularAttribute<Ciudades, String> ciudad;
    public static volatile SingularAttribute<Ciudades, Integer> id;

}