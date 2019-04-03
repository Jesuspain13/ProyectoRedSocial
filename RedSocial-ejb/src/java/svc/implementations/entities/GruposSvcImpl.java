/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svc.implementations.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import redSocial.dao.GruposFacadeLocal;
import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.Searchable;
import redSocial.svc.interfaces.entities.GruposSvc;
import redSocial.svc.interfaces.entities.UsuarioSvc;

/**
 *
 * @author Jesus
 */
@Stateless
public class GruposSvcImpl implements GruposSvc, Searchable {
    
    private static final String REQUEST_NAME_OF_GROUP = "nombregrupo";
    
    @EJB
    public GruposFacadeLocal groupDao;
    
    @EJB
    public UsuarioSvc userSvc;
    
    
    @Override
    public Usuario buildGroup(Grupos groupCreated, Usuario user/*HttpServletRequest request, int toDo*/) throws Exception {
        try {

            groupDao.create(groupCreated);
            user.getGruposList().add(groupCreated);
            
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public Usuario followGroup(int idGroupToFollow, Usuario user) {
        Grupos groupToFollow = groupDao.find(idGroupToFollow);
        groupToFollow.getUsuarioList().add(user);
        groupDao.edit(groupToFollow);
        user.getGruposList().add(groupToFollow);
        return user;
    }
    
    public boolean unfollowGroup(int idGroupToUnfollow, Usuario user) {

        Grupos groupToUnfollow = this.findInListById(idGroupToUnfollow, user.getGruposList());
        user.getGruposList().remove(groupToUnfollow);
        groupToUnfollow.getUsuarioList().remove(user);
        groupDao.edit(groupToUnfollow);
        return true;

    }
    
    public void updateGroup(Grupos groupToUpdate) throws Exception {
        try {
            groupDao.edit(groupToUpdate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public Grupos findInListById(Integer id, List listToSearch) {
        Grupos result = null;
        boolean encontrado = false;
        Iterator list = listToSearch.iterator();
        while (list.hasNext() && !encontrado) {
            Grupos iteration = (Grupos) list.next();
            if (iteration.getIdGrupo() == id) {
                result = iteration;
            }
        }
        return result;
    }
    
    @Override
    public List<Grupos> findOtherGroups(Usuario user) {
        try {
            List<Grupos> otherGroupsFound = groupDao.GroupList(user);
            return otherGroupsFound;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } 
    }
    
    @Override
    public Grupos findGroupInDB(Integer id) {
        Grupos groupFound = groupDao.find(id);
        return groupFound;
    }
}
