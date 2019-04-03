/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.interfaces.entities;

import javax.ejb.Local;
import redSocial.modelos.Post;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.Deletable;

/**
 *
 * @author Jesus
 */
@Local
public interface PostSvc extends Deletable {
    
    public void buildPost(Post postCreated, Usuario user);
    
}
