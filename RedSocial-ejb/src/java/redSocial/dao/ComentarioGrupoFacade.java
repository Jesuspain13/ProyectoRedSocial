/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import redSocial.modelos.ComentarioGrupo;

/**
 *
 * @author Jesus
 */
@Stateless
public class ComentarioGrupoFacade extends AbstractFacade<ComentarioGrupo> implements ComentarioGrupoFacadeLocal {

    @PersistenceContext(unitName = "RedSocial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentarioGrupoFacade() {
        super(ComentarioGrupo.class);
    }
    
}
