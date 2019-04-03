/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.dao;

import java.util.List;
import javax.ejb.Local;
import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;

/**
 *
 * @author Jesus
 */
@Local
public interface GruposFacadeLocal {

    void create(Grupos grupos);

    void edit(Grupos grupos);

    void remove(Grupos grupos);

    Grupos find(Object id);

    List<Grupos> findAll();

    List<Grupos> findRange(int[] range);

    int count();
    
    List<Grupos> GroupList(Usuario user) throws Exception;
}
