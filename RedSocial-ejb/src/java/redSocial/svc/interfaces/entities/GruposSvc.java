/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.interfaces.entities;

import java.util.List;
import javax.ejb.Local;
import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.Searchable;

/**
 *
 * @author Jesus
 */
@Local
public interface GruposSvc extends Searchable {
    
    
    public Usuario buildGroup(Grupos groupCreated, Usuario user) throws Exception;
    
    public Usuario followGroup(int idGroupToFollow, Usuario user);
    
    public boolean unfollowGroup(int idGroupToUnfollow, Usuario user);
    
    public void updateGroup(Grupos groupToUpdate) throws Exception;
    
    public List<Grupos> findOtherGroups(Usuario user);
    
    public Grupos findGroupInDB(Integer id);
}
