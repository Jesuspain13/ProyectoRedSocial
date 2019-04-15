/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import redSocial.modelos.ComentarioGrupo;
import redSocial.svc.interfaces.entities.ComentarioSvc;
import redSocial.svc.interfaces.entities.UsuarioSvc;

/**
 *
 * @author Jesus
 */
@javax.enterprise.context.RequestScoped
@Path("redsocial.modelos.comentariogrupo")
public class ComentarioGrupoFacadeREST {

    @Inject
    private UsuarioSvc userSvc;
    
    @Inject
    private ComentarioSvc commentSvc;

    @POST
    @Consumes({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    public void create(ComentarioGrupo entity) {
        
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, ComentarioGrupo entity) {
       // super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
       // super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ComentarioGrupo find(@PathParam("id") Integer id) {
       // return super.find(id);
       return null;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ComentarioGrupo> findAll() {
//        return super.findAll();
        return null;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ComentarioGrupo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        //return super.findRange(new int[]{from, to});
        return null;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        //return String.valueOf(super.count());
        return null;
    }

    
}
