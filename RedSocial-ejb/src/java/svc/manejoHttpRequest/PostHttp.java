/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svc.manejoHttpRequest;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import redSocial.modelos.Post;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.entities.PostSvc;
import svc.implementations.entities.UsuarioSvcImpl;

/**
 *
 * @author Jesus
 */
@Stateless
public class PostHttp {
    
    @EJB
    public PostSvc postSvc;
    
    public void crearPost(HttpServletRequest request) {
        Usuario user = UsuarioSvcImpl.getUserFromSession(request);
        Post newPost = new Post();
            newPost.setContenido(request.getParameter("content"));
            newPost.setIdPublicador(user);
            newPost.setFecha(new Date());
        postSvc.buildPost(newPost, user);
    }
    
    public Usuario borrarPost(HttpServletRequest request) {
        Usuario user = UsuarioSvcImpl.getUserFromSession(request);
        int idPostToDelete = Integer.parseInt(
                request.getParameter("postToDelete"));
        user = (Usuario) postSvc.delete(user, idPostToDelete);
        return user;
    }
    
}
