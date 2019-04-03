/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svc.implementations.entities;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import redSocial.dao.ComentarioGrupoFacadeLocal;
import redSocial.modelos.ComentarioGrupo;
import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.Searchable;
import redSocial.svc.interfaces.entities.ComentarioSvc;
import redSocial.svc.interfaces.entities.GruposSvc;
import redSocial.svc.interfaces.entities.UsuarioSvc;

/**
 *
 * @author Jesus
 */
@Stateless
public class ComentarioSvcImpl implements ComentarioSvc, Searchable {
    
    private static final String ID_GRUPO_SELECTED = "privacidad";
    private static final String ID_COMMENT_SELECTED = "id_comentario";
    
    @EJB
    private UsuarioSvc userSvc;
    
    @EJB
    private GruposSvc groupSvc;
    
    @EJB 
    private ComentarioGrupoFacadeLocal commentDao;

    @Override
    public Usuario buildComment(ComentarioGrupo comment, int idGrupoToPublish, Usuario user/*HttpServletRequest request*/) throws Exception {
        ComentarioGrupo commentCreated = comment;

        Grupos groupSelected = (Grupos) groupSvc
                .findInListById(idGrupoToPublish, user.getGruposList());
        comment.setIdGrupo(groupSelected);

        user.getComentarioGrupoList().add(comment);
        groupSelected.getComentarioGrupoList().add(comment);

        commentDao.create(comment);

        return user;

    }
    
    

    @Override
    public ComentarioGrupo findInListById(Integer id, List listToSearch) {
        ComentarioGrupo result = null;
        boolean encontrado = false;
        Iterator list = listToSearch.iterator();
        while (list.hasNext() && !encontrado) {
            ComentarioGrupo iteration = (ComentarioGrupo) list.next();
            if (iteration.getIdComentario() == id) {
                result = iteration;
            }
            
        }
        return result;
    }

    @Override
    public Usuario delete(Usuario user, int idCommentToDelete) {
        ComentarioGrupo commentToDelete =  (ComentarioGrupo) this
                .findInListById(idCommentToDelete, user.getComentarioGrupoList());
        user.getComentarioGrupoList().remove(commentToDelete);
        commentDao.remove(commentToDelete);
        commentToDelete.getIdGrupo().getComentarioGrupoList().remove(commentToDelete);
        
        return user;
    }
    
}
