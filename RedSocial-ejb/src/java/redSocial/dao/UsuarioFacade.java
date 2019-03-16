/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import redSocial.modelos.Amistades;
import redSocial.modelos.Usuario;

/**
 *
 * @author Jesus
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "RedSocial-ejbPU")
    private EntityManager em;
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario findByEmail (String email) throws Exception {
        try {
            String sql = "FROM Usuario u WHERE u.email=:email ";
            Usuario res = (Usuario) em.createQuery(sql)
                    .setParameter("email", email)
                    .getSingleResult();
                    
            return res;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    
    public Usuario findByEmailAndPassword(String email, String password) throws Exception {
        try {
            String sql = "FROM Usuario u WHERE u.email=:email "
                    + "AND u.password=:password";
            Usuario res = (Usuario) em.createQuery(sql)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
                    
            return res;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    
    public List<Usuario> findOtherUsers(Usuario user) throws Exception {

            List<Amistades> listaAmist = user.getAmistadesList();
            List<Usuario> misAmigos= new ArrayList ();
            for (Amistades amistad: listaAmist) {
                misAmigos.add(amistad.getIdUsuario2());
                
            }
            
            misAmigos.add(user);
       
            List<Usuario> res = this.findAll();
            res.removeAll(misAmigos);
            return res;
            
           
    }
    
}
