package redSocial.modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import redSocial.modelos.ComentariosGrupos;
import redSocial.modelos.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-18T11:02:09")
@StaticMetamodel(Grupos.class)
public class Grupos_ { 

    public static volatile ListAttribute<Grupos, Usuario> usuarioList;
    public static volatile SingularAttribute<Grupos, String> nombregrupo;
    public static volatile ListAttribute<Grupos, ComentariosGrupos> comentariosList;
    public static volatile SingularAttribute<Grupos, Usuario> idUsuario;
    public static volatile CollectionAttribute<Grupos, ComentariosGrupos> comentariosGruposCollection;
    public static volatile SingularAttribute<Grupos, Integer> idgrupos;

}