package redSocial.modelos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import redSocial.modelos.Privacidad;
import redSocial.modelos.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-15T17:46:17")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, Date> fecha;
    public static volatile SingularAttribute<Post, String> contenido;
    public static volatile SingularAttribute<Post, Usuario> idUsuario;
    public static volatile SingularAttribute<Post, Integer> idPost;
    public static volatile SingularAttribute<Post, Privacidad> privacidad;

}