/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.implementations;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import redSocial.dao.AmistadesFacade;
import redSocial.dao.ComentariosGruposFacade;
import redSocial.dao.GruposFacade;
import redSocial.dao.PostFacade;
import redSocial.modelos.Amistades;
import redSocial.modelos.ComentariosGrupos;
import redSocial.modelos.Grupos;
import redSocial.modelos.Post;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.Deletable;
import redSocial.svc.interfaces.Searchable;
import redSocial.svc.interfaces.Upgradeable;

/**
 *
 * @author Jesus
 */
@Stateless
public class DeletableImpl implements Deletable {

    @EJB
    private AmistadesFacade friendsDao;
            
    @EJB
    private PostFacade postDao;
    
    @EJB
    private GruposFacade gruposDao;
    
    @EJB
    private ComentariosGruposFacade commentDao;
    
    private Upgradeable upgradeableObj = UpgradeableImpl
            .getUpgradeableImplFactory();
    
    private Searchable searchableObj = SearchableImpl
            .getSearchableImplFactory();
  
    @Override
    public void delete(
            Object user, int id, int toSearch /*Object thingToCreate*/) {
        Object thingToUpdate = null;
        // amistades
        if (toSearch == 1) {
            Usuario userCast = (Usuario) user;
            Amistades relationship = (Amistades) searchableObj
                    .search(userCast.getAmistadesList(), id);
            friendsDao.remove(relationship);
            thingToUpdate = (Amistades) relationship;
            upgradeableObj.updateUser(userCast, thingToUpdate, 2);
            // post
        } else if (toSearch == 2) {
            Usuario userCast = (Usuario) user;
            Post post = (Post) searchableObj
                    .search(userCast.getPostList(), id);
            postDao.remove(post);
            thingToUpdate = (Post) post;
            upgradeableObj.updateUser(userCast, thingToUpdate, 2);
            // grupos
        } else if (toSearch == 3) {
            Usuario userCast = (Usuario) user;
            Grupos grupo = (Grupos) searchableObj
                    .search(userCast.getGruposList(), id);
            List<Usuario> groupUsers = grupo.getUsuarioList();
            groupUsers.remove(userCast);
            grupo.setUsuarioList(groupUsers);
            gruposDao.edit(grupo);
            thingToUpdate = grupo;
            upgradeableObj.updateUser(userCast, thingToUpdate, 2);
            // comentario grupo
        } else if (toSearch == 4) {
            Usuario userCast = (Usuario) user;
            ComentariosGrupos commentToDelete = commentDao.find(id);
            commentDao.remove(commentToDelete);
            thingToUpdate = commentToDelete;
            upgradeableObj.updateUser(userCast, thingToUpdate, 2);
        }
        
        //if (thingToCreate instanceof Amistades) {
//           Amistades amistad = (Amistades) thingToCreate;
//           friendsDao.remove(amistad);
//        } else if (thingToCreate instanceof Post) {
//            postDao.remove((Post)thingToCreate);    
//        } else if (thingToCreate instanceof Grupos) {
//            gruposDao.remove((Grupos)thingToCreate);
//        }
        //upgradeableObj.updateUser(user, thingToUpdate, 2);
        
    }
    
}

