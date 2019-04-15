/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.rest;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import redSocial.dao.UsuarioFacadeLocal;
import redSocial.modelos.EncapsularInfoPost;
import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.entities.GruposSvc;

/**
 *
 * @author Jesus
 */
@javax.enterprise.context.RequestScoped

@Path("groups")
public class GruposFacadeREST {
    @Inject
    private UsuarioFacadeLocal userDao;
    
    @Inject
    private GruposSvc groupSvc;
    

    @POST
    @Path("creategroup")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(EncapsularInfoPost entity) {
        try {
            Usuario user = userDao.find(entity.getIdUsuario());
            Grupos grupo = entity.getGrupo();
            
            //da error
            List<Usuario> list = new ArrayList();
            list.add(user);
            grupo.setUsuarioList(list);
            groupSvc.buildGroup(grupo, user);
            Map<String,String> result = new HashMap<String, String>();
            Gson gson = new Gson();
            GenericEntity resultVoid = new GenericEntity("All ok", String.class);
            return Response.ok().entity(gson.toJson(result)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_GATEWAY).build();
        }
        
    }

    @POST
    @Path("followgroup")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response followGroup(EncapsularInfoPost entity) {
        Usuario user = userDao.find(entity.getIdUsuario());
        groupSvc.followGroup(entity.getGrupo().getIdGrupo(), user);
        Map<String,String> result = new HashMap<String, String>();
        Gson gson = new Gson();
        return Response.ok().entity(gson.toJson(result)).build();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        //super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Grupos find(@PathParam("id") Integer id) {
        //return super.find(id);
        return null;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Grupos> findAll() {
        //return super.findAll();
        return null;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Grupos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        //return super.findRange(new int[]{from, to});
        return null;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
     //   return String.valueOf(super.count());
        return null;
    }

}
