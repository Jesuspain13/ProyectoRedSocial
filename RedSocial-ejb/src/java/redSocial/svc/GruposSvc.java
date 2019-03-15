/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import redSocial.dao.GruposFacade;
import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;

/**
 *
 * @author Jesus
 */
@Stateless
public class GruposSvc {
    
    @EJB
    private GruposFacade gruposDao;
    
    public List<Grupos> encontrarOtrosGrupos(Usuario user) {
        List<Grupos> todos = gruposDao.findAll();
        List<Grupos> gruposUsuario = user.getGruposList();
        for(Grupos grupo: gruposUsuario) {
            if (todos.contains(grupo)) {
                todos.remove(grupo);
            }
        }
        return todos;
    }
}
