package redSocial.modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import redSocial.modelos.Post;
import redSocial.modelos.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-07T18:32:44")
@StaticMetamodel(Privacidad.class)
public class Privacidad_ { 

    public static volatile SingularAttribute<Privacidad, String> tipo;
    public static volatile ListAttribute<Privacidad, Post> postList;
    public static volatile SingularAttribute<Privacidad, Usuario> usuario;
    public static volatile SingularAttribute<Privacidad, Integer> id;

}