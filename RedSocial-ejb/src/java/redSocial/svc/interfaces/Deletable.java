/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.interfaces;

import redSocial.modelos.Usuario;

/**
 *
 * @author Jesus
 */

public interface Deletable {
    
    /**
     * metodo para borrar un registro en la base de datos y modificar el usuario
     * @param user usuario a modificar
     * @param id id que del registro que se quiere elmiminar
     * @param toSearch numero que representa que se va a buscar
     */
    public void delete(Usuario user, int id, int toSearch /*Object thingToCreate*/);
    
}
