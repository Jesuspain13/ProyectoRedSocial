/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svc.manejoHttpRequest;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import redSocial.dao.UsuarioFacadeLocal;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.entities.UsuarioSvc;
import static svc.implementations.entities.UsuarioSvcImpl.getUserFromSession;

/**
 *
 * @author Jesus
 */
@Stateless
public class UsuarioHttp {
    
    private static final String SESSION_USER_ATTRIB = "usuario";

    
    @EJB
    private UsuarioSvc userSvc;
    
    @EJB
    private UsuarioFacadeLocal userDao;
    
    public void construirUsuario(HttpServletRequest request) throws Exception {
        try {
            String email = request.getParameter("email");
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String telefono = request.getParameter("telefono");
            String password = request.getParameter("password");

            Usuario user = new Usuario();
            
            user.setEmail(email);
            user.setNombre(nombre);
            user.setApellidos(apellidos);
            user.setTelefono(telefono);
            user.setPassword(password);
            
            userSvc.buildUser(user);
        } catch (Exception ex) {
            ex.printStackTrace();
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
    
    public void nuevoAmigoPorEmail(HttpServletRequest request) {
        Usuario user = getUserFromSession(request);
        
        String newFriendFound = request.getParameter("newFriendEmail");
        userSvc.newFriendByEmail(user, newFriendFound);
    }
    
    public void nuevoAmigoYaExistente(HttpServletRequest request) {
        Usuario user = getUserFromSession(request);
        int id = Integer.parseInt(request.getParameter("idNewFriend"));
        //newFriendFound = userDao.find(id);
        userSvc.newFriendById(user, id);
    }
    
    public List<Usuario> encontrarOtrosUsuarios(HttpServletRequest request) {
        Usuario user = getUserFromSession(request);
        List<Usuario> result = userSvc.findOtherUsers(user);
        return result;
    }
    
    public Usuario dejarDeSeguir(HttpServletRequest request) {
        Usuario user = getUserFromSession(request);
        if (request.getParameter("friendToUnfollow") != null) {
            int id = Integer.parseInt(request.getParameter("friendToUnfollow"));
            user = (Usuario) userSvc.delete(user, id);
        }
        return user;
    }
    
    public Usuario borrarTodo(HttpServletRequest request) {
        Usuario user = getUserFromSession(request);
        user = userSvc.removeAll(user);
        return user;
    }
    
    public Usuario logearse(HttpServletRequest request) {
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");
        Usuario user = userSvc.loginByEmailAndPass(email, password);
        return user;
    }
}
