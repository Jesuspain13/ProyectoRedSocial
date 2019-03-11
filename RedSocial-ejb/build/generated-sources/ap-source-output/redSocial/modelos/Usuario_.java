package redSocial.modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import redSocial.modelos.Amistades;
import redSocial.modelos.Grupos;
import redSocial.modelos.Post;
import redSocial.modelos.Privacidad;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-11T16:08:39")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile ListAttribute<Usuario, Grupos> gruposList;
    public static volatile ListAttribute<Usuario, Amistades> amistadesList;
    public static volatile ListAttribute<Usuario, Post> postList;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, String> telefono;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile ListAttribute<Usuario, Grupos> gruposList1;
    public static volatile ListAttribute<Usuario, Amistades> amistadesList1;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile SingularAttribute<Usuario, Privacidad> privacidad;

}