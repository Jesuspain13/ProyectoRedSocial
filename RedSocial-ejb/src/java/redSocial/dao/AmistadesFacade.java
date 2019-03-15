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
import redSocial.modelos.Amistades;
import redSocial.modelos.Usuario;

/**
 *
 * @author Jesus
 */
@Stateless
public class AmistadesFacade extends AbstractFacade<Amistades> {

    @PersistenceContext(unitName = "RedSocial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AmistadesFacade() {
        super(Amistades.class);
    }
    
    public List<Amistades> findByUsuario(Usuario idUsuarioABuscar) throws Exception {
        try {
            String query = "FROM Amistades a WHERE a.idUsuario1 = :usuario "
                    + "OR a.idUsuario2 = :usuario";
            
            List res = em.createQuery(query)
                    .setParameter("usuario", idUsuarioABuscar)
                    .getResultList();
            
            return res;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
