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
import redSocial.modelos.ComentarioGrupo;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.entities.ComentarioSvc;
import svc.implementations.entities.UsuarioSvcImpl;

/**
 *
 * @author Jesus
 */
@Stateless
public class ComentarioHttp {
    
    private static final String ID_GRUPO_SELECTED = "privacidad";

    
    @EJB
    public ComentarioSvc commentSvc;
    
    public Usuario construirComentario(HttpServletRequest request) {
        try {
            Usuario user = UsuarioSvcImpl.getUserFromSession(request);
            if (!request.getParameter("content").isEmpty()) {
                //si no está el id del comentario es que se va a crear nuevo
                ComentarioGrupo comment = new ComentarioGrupo();
                comment.setComentario(request.getParameter("content"));
                comment.setFecha(new Date());
                comment.setIdAutor(user);

                int idGrupoToPublish = Integer.parseInt(request.getParameter(ID_GRUPO_SELECTED));

                user = commentSvc.buildComment(comment, idGrupoToPublish, user);
            }
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public Usuario borrarComentario(HttpServletRequest request) {
        Usuario user = UsuarioSvcImpl.getUserFromSession(request);
        int idCommentToDelete;
        if (request.getParameter("commentToDelete") != null || 
            Integer.parseInt(request.getParameter("commentToDelete")) > 0) {
            //si está el parámetro, es que se va a eliminar el comentario
            idCommentToDelete = Integer.parseInt(request.getParameter("commentToDelete"));
            user = (Usuario) commentSvc.delete(user, idCommentToDelete);
        }
        return user;
        
    }
}
