/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import redSocial.dao.AmistadesFacade;
import redSocial.modelos.Amistades;
import redSocial.modelos.Post;
import redSocial.modelos.Usuario;

/**
 *
 * @author Jesus
 */
@Stateless
public class AmistadesSvc {
    
    @EJB
    private AmistadesFacade dao;
    
    public List<Post> buscarPostDeAmigos(Usuario id) throws Exception {
        try {
            List<Amistades> res = dao.findByUsuario(id);
            Iterator amistadesIterador = res.iterator();
            List<Post> postsAMostrar = new ArrayList();
            int contador = 0;
            Amistades amistades;
            while(amistadesIterador.hasNext() && postsAMostrar.size() < 3) {            
                amistades = (Amistades) amistadesIterador.next();
                postsAMostrar.add(res.get(contador).getIdUsuario2()
                        .getPostList().get(0));
                contador++;
            }
            return postsAMostrar;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
