/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import redSocial.dao.UsuarioFacadeLocal;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.entities.UsuarioSvc;

/**
 *
 * @author Jesus
 */
@javax.enterprise.context.RequestScoped
@Path("redsocial")
public class UsuarioFacadeREST {
    
    private Gson gson;

    
    @Inject
    private UsuarioSvc userSvc;
    
    //Error al usar EJB!!!! pregunta!!
    //El json que me devuelve no tiene los atributos que muestran las listas indirectas
    @Inject
    private UsuarioFacadeLocal userDao;

    @GET
    @Path("/login")
 //   @Consumes({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response hacerLogin(/*Usuario entity*/) {
        //Usuario user = userSvc.loginByEmailAndPass("jespana@uma.es", "22222");
        Usuario user = userSvc.loginByEmailAndPass("jespana@uma.es", "22222");
        String userJson = Usuario.toJson(user);
        GenericEntity entity = 
                        new GenericEntity(userJson, String.class);
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Path("/addfriend/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void añadirAmigo(@PathParam("id") Integer id, Usuario entity) {
        
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        //super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario find(@PathParam("id") Integer id) {
        //return super.find(id);
        return null;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        //return super.findAll();
        return null;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
