/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.interfaces;

import java.util.List;
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
    
    /**
     * cambia una lista
     * @param listToChange lista a cambiar
     * @param userToAdd usuario a añadir o borrar de la lista
     * @param toDo añadir o borrar (1 o 2 respectivamente)
     * @throws Exception 
     */
    public void changeList(List listToChange, Object userToAdd, int toDo) throws Exception;
        
    
}
