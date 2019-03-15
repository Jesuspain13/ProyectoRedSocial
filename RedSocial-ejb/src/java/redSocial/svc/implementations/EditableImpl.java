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
import redSocial.dao.GruposFacade;
import redSocial.modelos.Amistades;
import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.Editable;
import redSocial.svc.interfaces.Upgradeable;

/**
 *
 * @author Jesus
 */
@Stateless
public class EditableImpl implements Editable {
    
    @EJB
    public AmistadesFacade friendsDao;
    
    @EJB
    public GruposFacade groupDao;
    
    @EJB
    public Upgradeable upgradeableObj;
    

    @Override
    public void editable(Usuario user, Object thingUpdated, int toDo) throws Exception {
        try {
            if (thingUpdated instanceof Amistades) {
               friendsDao.edit((Amistades)thingUpdated);
            } else if (thingUpdated instanceof Grupos) {
                Grupos groupChanged = (Grupos)thingUpdated;
                groupDao.edit(groupChanged);
                upgradeableObj.updateGroupComponents(groupChanged, user, toDo);
                upgradeableObj.updateUser(user, groupChanged, toDo);
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    
    @Override
    public void changeGroup(Grupos groupToChange, Usuario userToAdd, int toDo) throws Exception {
        try {
            List<Usuario> olderUsuario = groupToChange.getUsuarioList();
            if (toDo == 1) {
                olderUsuario.add(userToAdd);
            } else if (toDo == 2) {
                olderUsuario.remove(userToAdd);
            }
            groupToChange.setUsuarioList(olderUsuario);
            this.editable(userToAdd, groupToChange, toDo);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    
    }
    
}
