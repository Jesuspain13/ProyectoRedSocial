/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.interfaces;

import redSocial.modelos.Amistades;
import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;

/**
 *
 * @author Jesus
 */
public interface Editable {
    
    public void editable(Usuario user, Object thingUpdated, int toDo) throws Exception;
    
    /**
     * cambiar la lista de usuarios de un grupo
     * @param groupToChange grupo a cambiar
     * @param userToAdd usuario a añadir o borrar
     * @param toDo que hacer, añadir o borrar
     * @throws Exception 
     */
    public void changeGroup(Grupos groupToChange, Usuario userToAdd, int toDo) throws Exception;
        
    
}
