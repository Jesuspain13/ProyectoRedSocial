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
public interface Creatable {
    
    /**
     * metodo para crear un registro en la base de datos y modificar el usuario
     * @param user usuario a modificar
     * @param thingToCreate registro a crear
     */
    public void create(Usuario user, Object thingToCreate);
    
    /**
     * Construye una Amistad y llama al create
     * @param user usuario que añade a alguien
     * @param friendEmail email de quien va a añadir
     * @throws Exception 
     */
    public void buildRelationship(Usuario user, Object friendEmail) throws Exception;
    
    /**
     * Construye un comentario
     * @param user usuario que comenta
     * @param content contenido del comentario
     * @param groupId grupo en el que comenta
     * @throws Exception 
     */
    public void buildComment(Usuario user, String content, int groupId) throws Exception;
    
    /**
     * Construye un comentario
     * @param user usuario que comenta
     * @param nameGroup nombre del grupo a crear
     * @throws Exception 
     */
    public void buildGroup(Usuario user, String nameGroup) throws Exception;
    
    /**
     * Construye un post
     * @param user usuario que escribe el post
     * @param contenido lo que escribe
     * @throws Exception 
     */
    public void buildPost(Usuario user, String contenido) throws Exception;
}
