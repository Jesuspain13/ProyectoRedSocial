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
import redSocial.dao.PostFacadeLocal;
import redSocial.modelos.Post;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.Deletable;
import redSocial.svc.interfaces.Searchable;
import redSocial.svc.interfaces.entities.PostSvc;
import redSocial.svc.interfaces.entities.UsuarioSvc;

/**
 *
 * @author Jesus
 */
@Stateless
public class PostSvcImpl implements PostSvc, Searchable, Deletable {
    
    @EJB
    public UsuarioSvc userSvc;
    
    @EJB
    public PostFacadeLocal postDao;


    @Override
    public Post findInListById(Integer id, List listToSearch) {
        Post result = null;
        boolean encontrado = false;
        Iterator list = listToSearch.iterator();
        while (list.hasNext() && !encontrado) {
            Post iteration = (Post) list.next();
            if (iteration.getIdPost()== id) {
                result = iteration;
            }
            
        }
        return result;
    }

    @Override
    public Usuario delete(Usuario user, int idPostToDelete) {

        Post post = this.findInListById(idPostToDelete, user.getPostList());
        postDao.remove(post);
        user.getPostList().remove(post);

        return user;
    }
    
    
    @Override
    public void buildPost(Post postCreated, Usuario user) {
        
        user.getPostList().add(postCreated);
        userSvc.updateUser(user);
           
    }
    
}
