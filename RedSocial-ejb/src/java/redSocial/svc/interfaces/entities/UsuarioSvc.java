/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.interfaces.entities;

import java.util.List;
import javax.ejb.Local;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.Deletable;

/**
 *
 * @author Jesus
 */
@Local
public interface UsuarioSvc extends Deletable {

    /**
     * crea un usuario registrado y lo guarda en la bas de datos
     * @param request petición post con los datos del usuario
     * @throws Exception 
     */
    public Usuario buildUser( Usuario userCreated) throws Exception;
    
    public void updateUser(Usuario userToUpdate);
    
    public void newFriendByEmail(Usuario user, String email);
    
    public void newFriendById(Usuario user, int id);
    
    public List<Usuario> findOtherUsers(Usuario user);
    
    public Usuario removeAll(Usuario user);
    
    public Usuario loginByEmailAndPass(String email, String password);
    
    
}
