/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.dao;

import java.util.List;
import javax.ejb.Local;
import redSocial.modelos.ComentarioGrupo;

/**
 *
 * @author Jesus
 */
@Local
public interface ComentarioGrupoFacadeLocal {

    void create(ComentarioGrupo comentarioGrupo);

    void edit(ComentarioGrupo comentarioGrupo);

    void remove(ComentarioGrupo comentarioGrupo);

    ComentarioGrupo find(Object id);

    List<ComentarioGrupo> findAll();

    List<ComentarioGrupo> findRange(int[] range);

    int count();
    
}
