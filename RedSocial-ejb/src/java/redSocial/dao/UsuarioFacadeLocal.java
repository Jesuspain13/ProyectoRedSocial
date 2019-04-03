/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.dao;

import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import redSocial.modelos.Usuario;

/**
 *
 * @author Jesus
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    Usuario findByEmailAndPass(String email, String password);
    
    Usuario findByEmail(String email);

    
}
