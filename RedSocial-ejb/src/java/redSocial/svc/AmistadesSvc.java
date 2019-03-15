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
import redSocial.dao.UsuarioFacade;
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
    private AmistadesFacade amistadesDao;
    
    @EJB
    private UsuarioFacade userDao;
    
    /**
     * busca los post de los amigos del usuario
     * @param usuario usuario que se ha logueado
     * @return devuelve una lista con el ultimo post de cada amigo
     * @throws Exception 
     */
    public List<Post> buscarPostDeAmigos(Usuario usuario) throws Exception {
        try {
            /*
            List<Amistades> res = dao.findByUsuario(id);
            Iterator amistadesIterador = res.iterator();
            List<Post> postsAMostrar = new ArrayList();
            int contador = 0;
            Amistades amistades;
            while(amistadesIterador.hasNext() && postsAMostrar.size() < 3) {            
                amistades = (Amistades) amistadesIterador.next();
                postsAMostrar.add(amistades.getIdUsuario2()
                        .getPostList().get(0));
                contador++;
            
            }
            return postsAMostrar;
        */
            
            List<Post> postDelAmigo = new ArrayList();
            Iterator amistades = usuario.getAmistadesList().iterator();
            Amistades a;
            while(amistades.hasNext()) {
                a = (Amistades) amistades.next();
                postDelAmigo.add(a.getIdUsuario2().getPostList().get(0));
            }
        
            return postDelAmigo;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    
    public Amistades nuevoAmigo(Usuario perfilUsuario, String correoElectronico) 
            throws Exception {
        try {
            Usuario nuevoAmigo = userDao.findByEmail(correoElectronico);
            //crea la amistad en una dirección 1 -> 2
            Amistades nuevaAmistad = new Amistades();
            nuevaAmistad.setIdUsuario1(perfilUsuario);
            nuevaAmistad.setIdUsuario2(nuevoAmigo);
            return nuevaAmistad;
//            amistadesDao.create(nuevaAmistad);
//            
//            List<Amistades> antiguasAmistades = perfilUsuario.getAmistadesList();
//            antiguasAmistades.add(nuevaAmistad);
//            perfilUsuario.setAmistadesList(antiguasAmistades);
            
            //crear la amistad en la dirección opuesta 2 -> 1
            //AmistadesSvc.cambiarSentido(nuevaAmistad);
            //al pasar el parametro como una referencia a memoria los cambios deberian afectar al mismo objeto
            //dao.create(nuevaAmistad);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    
    private static Amistades cambiarSentido(Amistades amistadAIntercambiar) 
            throws Exception {
        Usuario aux = amistadAIntercambiar.getIdUsuario1();
        amistadAIntercambiar.setIdUsuario1(amistadAIntercambiar.getIdUsuario2());
        amistadAIntercambiar.setIdUsuario2(aux);
        return amistadAIntercambiar;
    }
}
