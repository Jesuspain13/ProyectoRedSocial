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
import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;

/**
 *
 * @author Jesus
 */
@Stateless
public class GruposFacade extends AbstractFacade<Grupos> {

    @PersistenceContext(unitName = "RedSocial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GruposFacade() {
        super(Grupos.class);
    }
    
    public List<Grupos> GroupList(Usuario user) throws Exception {

        try {
            String sql  = "SELECT DISTINCT g FROM Grupos g WHERE :user  NOT MEMBER OF g.usuarioList";
            List<Grupos> res =  em.createQuery(sql).setParameter("user", user).getResultList();
            return  res;
        } catch (Exception ex) {
            throw new Exception(ex);
        }

    }
    
    
    
}
