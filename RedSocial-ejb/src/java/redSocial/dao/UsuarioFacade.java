/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import redSocial.modelos.Usuario;

/**
 *
 * @author Jesus
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "RedSocial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario findByEmailAndPass(String email, String password) {
        String sql = "FROM Usuario u WHERE u.email=:userEmail AND u.password=:userPassword";
        Usuario userFound = (Usuario) em.createQuery(sql)
                .setParameter("userEmail", email)
                .setParameter("userPassword", password)
                .getSingleResult();
        
        return userFound;
                
    }

    @Override
    public Usuario findByEmail(String email) {
        
        String ql = "FROM Usuario u WHERE u.email = :email";
        Usuario friendFound = (Usuario) em.createQuery(ql)
                .setParameter("email", email)
                .getSingleResult();
        return friendFound;
    }
    
}
