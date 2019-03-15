/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.interfaces;

import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;

/**
 *
 * @author Jesus
 */
public interface Upgradeable {
    /**
     * Se encarga de actualizar el usuario cuando se añade o borra algo de su 
     * perfil
     * @param user usuario que va a ser actualizado
     * @param thingToAddOrDelete lo que se pretende añadir o borrar
     * @param toDo representa si se quiee añadir o borrar
     */
    public void updateUser(Usuario user, Object thingToAddOrDelete, int toDo);
    
    /**
     * Se encarga de actualizar el usuario cuando se añade o borra algo de su 
     * perfil
     * @param grupoaActualizar grupo que va a ser actualizado
     * @param thingToAddOrDelete lo que se pretende añadir o borrar
     * @param toDo representa si se quiee añadir o borrar
     */
    public void updateGroupComponents(Grupos grupoaActualizar, Object thingToAddOrDelete, int toDo);
    
    
    
}

