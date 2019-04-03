/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svc.manejoHttpRequest;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.entities.GruposSvc;

/**
 *
 * @author Jesus
 */
@Stateless
public class GrupoHttp {
    
    @EJB
    private GruposSvc svc;
    
    
    public Usuario construirGrupo(HttpServletRequest request) {
        try {
            Usuario user = UsuarioHttp.getUserFromSession(request);
            Grupos newGroup = new Grupos();
            newGroup.setNombre(request.getParameter("groupNameToCreate"));
            List<Usuario> members = new ArrayList();
            members.add(user);
            newGroup.setUsuarioList(members);
            user = svc.buildGroup(newGroup, user);
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public Usuario unirseAGrupo(HttpServletRequest request) {
        try {
            Usuario user = UsuarioHttp.getUserFromSession(request);
            int idGroup = Integer.parseInt(request.getParameter("idGroupToFollow"));
            user = svc.followGroup(idGroup, user);
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void salirDeGrupo(HttpServletRequest request) {
        Usuario user = UsuarioHttp.getUserFromSession(request);
        int idGroupToUnfllow = Integer.parseInt(request.getParameter("idGroupToUnfollow"));
        svc.unfollowGroup(idGroupToUnfllow, user);
    }
    
    public List<Grupos> encontrarOtrosGrupos(HttpServletRequest request) {
        Usuario user = UsuarioHttp.getUserFromSession(request);
        List<Grupos> result = svc.findOtherGroups(user);
        return result;
    }
    
}
