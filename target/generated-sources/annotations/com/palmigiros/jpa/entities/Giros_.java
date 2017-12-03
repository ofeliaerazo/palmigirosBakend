package com.palmigiros.jpa.entities;

import com.palmigiros.jpa.entities.Ciudades;
import com.palmigiros.jpa.entities.Clientes;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-01T13:56:51")
@StaticMetamodel(Giros.class)
public class Giros_ { 

    public static volatile SingularAttribute<Giros, Date> fecha;
    public static volatile SingularAttribute<Giros, Boolean> estado;
    public static volatile SingularAttribute<Giros, BigDecimal> monto;
    public static volatile SingularAttribute<Giros, Ciudades> idCiudadEmisor;
    public static volatile SingularAttribute<Giros, Clientes> idClienteReceptor;
    public static volatile SingularAttribute<Giros, Integer> id;
    public static volatile SingularAttribute<Giros, Ciudades> idCiudadReceptor;
    public static volatile SingularAttribute<Giros, Clientes> idClienteEmisor;

}