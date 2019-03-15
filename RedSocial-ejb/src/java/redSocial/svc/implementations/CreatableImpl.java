/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import redSocial.dao.AmistadesFacade;
import redSocial.dao.ComentariosGruposFacade;
import redSocial.dao.GruposFacade;
import redSocial.dao.PostFacade;
import redSocial.dao.UsuarioFacade;
import redSocial.modelos.Amistades;
import redSocial.modelos.ComentariosGrupos;
import redSocial.modelos.Grupos;
import redSocial.modelos.Post;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.Creatable;
import redSocial.svc.interfaces.Searchable;
import redSocial.svc.interfaces.Upgradeable;

/**
 *
 * @author Jesus
 */
@Stateless
public class CreatableImpl implements Creatable {
    
    @EJB
    private AmistadesFacade friendsDao;
            
    @EJB
    private PostFacade postDao;
    
    @EJB
    private GruposFacade groupDao;
    
    @EJB
    private UsuarioFacade userDao;
    
    @EJB
    private ComentariosGruposFacade commentDao;
    
    private Searchable searchableObj = SearchableImpl
            .getSearchableImplFactory();
    
    private Upgradeable upgradeableObj = UpgradeableImpl
            .getUpgradeableImplFactory();
    

    @Override
    public void create(Usuario user, Object thingToCreate) {
        if (thingToCreate instanceof Amistades) {
           friendsDao.create((Amistades)thingToCreate);
        } else if (thingToCreate instanceof Post) {
            postDao.create((Post)thingToCreate);    
        } else if (thingToCreate instanceof Grupos) {
            groupDao.create((Grupos)thingToCreate);
        } else if (thingToCreate instanceof ComentariosGrupos) {
            commentDao.create((ComentariosGrupos)thingToCreate);
        }
        upgradeableObj.updateUser(user, thingToCreate, 1);
        
    }
    
    @Override
    public void buildRelationship(Usuario user, Object friendEmail) throws Exception {
        try {
            Usuario nuevoAmigo = null;
            if (friendEmail instanceof String) {
                nuevoAmigo = userDao.findByEmail((String)friendEmail);
            } else if (friendEmail instanceof Usuario) {
                nuevoAmigo = (Usuario) friendEmail;
            }
            Amistades newRelationship = new Amistades();
            newRelationship.setIdUsuario1(user);
            newRelationship.setIdUsuario2(nuevoAmigo);
            this.create(user, newRelationship);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    
    @Override
    public void buildComment(Usuario user, String content, int groupId) throws Exception {
        try {
            ComentariosGrupos comment = new ComentariosGrupos();
            comment.setComentario(content);
            
            comment.setIdGrupal((Grupos) searchableObj
                    .search(user.getGruposList(), groupId));
            comment.setIdPublicador(user);
            this.create(user, comment);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    
    @Override
    public void buildGroup(Usuario user, String nameGroup) throws Exception {
        try {
            Grupos newGroup = new Grupos();
            newGroup.setNombregrupo(nameGroup);
            newGroup.setIdUsuario(user);
            List<Usuario> members = new ArrayList();
            members.add(user);
            newGroup.setUsuarioList(members);
            this.create(user, newGroup);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    
    @Override
    public void buildPost(Usuario user, String contenido) throws Exception {
        try {
            Post newPost = new Post();
            newPost.setContenido(contenido);
            newPost.setIdUsuario(user);
            newPost.setFecha(new Date());
            this.create(user, newPost);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    
}
