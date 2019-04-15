/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svc.implementations.entities;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import redSocial.dao.UsuarioFacadeLocal;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.Deletable;
import redSocial.svc.interfaces.entities.UsuarioSvc;

/**
 *
 * @author Jesus
 */
@Stateless
public class UsuarioSvcImpl implements UsuarioSvc, Deletable {
    
    private static final String SESSION_USER_ATTRIB = "usuario";
    
    @EJB
    private UsuarioFacadeLocal userDao;


    @Override
    public Usuario buildUser(Usuario user) throws Exception {
        try {
            userDao.create(user);
            return userDao.findByEmailAndPass(user.getEmail(), user.getPassword());
        } catch (Exception ex) {
            throw new Exception(ex);
        }    
    }

    public static Usuario getUserFromSession(HttpServletRequest request) {
        try {
            Usuario user = (Usuario) 
                        request.getSession().getAttribute(SESSION_USER_ATTRIB);
            return user;
        } catch (Exception ex) {
            
            return null;
        }
    }

    @Override
    public void updateUser(Usuario userToUpdate) {
        userDao.edit(userToUpdate);
    }

    @Override
    public void newFriendByEmail(Usuario user, String email) {
        
        Usuario newFriendFound = userDao.findByEmail(email);

        user.getAmigosList().add(newFriendFound);
        updateUser(user);
    }
    
    @Override
    public void newFriendById(Usuario user, int id) {
        Usuario newFriendFound = userDao.find(id);
        user.getAmigosList().add(newFriendFound);
        updateUser(user);
    }

    @Override
    public List<Usuario> findOtherUsers(Usuario user) {
        
        List<Usuario> allUsers = userDao.findAll();
        List<Usuario> alreadyFriends = user.getAmigosList();
        allUsers.removeAll(alreadyFriends);
        allUsers.remove(user);
          
        return allUsers;
    }
    
    
    
    private Usuario findById(Integer id) {
        Usuario user = userDao.find(id);
        return user;
    }

    @Override
    public Usuario delete(Usuario user, int id) {

            Usuario friendToUnfollow = findById(id);
            user.getAmigosList().remove(friendToUnfollow);
            this.updateUser(user); 
         
        return user;
        
    }
    
    @Override
    public Usuario removeAll(Usuario user) {
        user.getAmigosList().clear();
        this.updateUser(user);
        return user;
    }
    
    @Override
    public Usuario loginByEmailAndPass(String email, String password) {
        Usuario user = userDao.findByEmailAndPass(email, password);
        return user;
    }

    
}
