/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.interfaces;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import redSocial.modelos.Usuario;

/**
 *
 * @author Jesus
 */
@Local
public interface Deletable {
    
    public Object delete(Usuario user, int id);
    
}
