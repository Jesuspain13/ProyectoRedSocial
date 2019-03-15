/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.implementations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import redSocial.dao.AmistadesFacade;
import redSocial.dao.GruposFacade;
import redSocial.dao.PostFacade;
import redSocial.modelos.Amistades;
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
    
    private Upgradeable upgradeableObj = UpgradeableImpl
            .getUpgradeableImplFactory();
    
    private Searchable searchableObj = SearchableImpl
            .getSearchableImplFactory();
  
    @Override
    public void delete(Usuario user, int id, int toSearch /*Object thingToCreate*/) {
        Object thingToUpdate = null;
        // amistades
        if (toSearch == 1) {
            Amistades relationship = (Amistades) searchableObj
                    .search(user.getAmistadesList(), id);
            friendsDao.remove(relationship);
            thingToUpdate = (Amistades) relationship;
            // post
        } else if (toSearch == 2) {
            Post post = (Post) searchableObj
                    .search(user.getPostList(), id);
            postDao.remove(post);
            thingToUpdate = (Post) post;
            // grupos
        } else if (toSearch == 3) {
            Grupos grupo = (Grupos) searchableObj
                    .search(user.getGruposList(), id);
            gruposDao.remove(grupo);
            thingToUpdate = (Grupos) grupo;
        }
        
        //if (thingToCreate instanceof Amistades) {
//           Amistades amistad = (Amistades) thingToCreate;
//           friendsDao.remove(amistad);
//        } else if (thingToCreate instanceof Post) {
//            postDao.remove((Post)thingToCreate);    
//        } else if (thingToCreate instanceof Grupos) {
//            gruposDao.remove((Grupos)thingToCreate);
//        }
        upgradeableObj.updateUser(user, thingToUpdate, 2);
        
    }
    
}

