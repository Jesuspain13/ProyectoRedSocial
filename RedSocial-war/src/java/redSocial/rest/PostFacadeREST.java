/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.rest;

import com.google.gson.Gson;
import java.util.Date;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import redSocial.dao.UsuarioFacadeLocal;
import redSocial.modelos.EncapsularInfoPost;
import redSocial.modelos.Post;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.entities.PostSvc;
import redSocial.svc.interfaces.entities.UsuarioSvc;

/**
 *
 * @author Jesus
 */
@javax.enterprise.context.RequestScoped

@Path("post")
public class PostFacadeREST{
    
    @Inject
    private UsuarioFacadeLocal userDao;
    
    @Inject
    private UsuarioSvc userSvc;
    
    @Inject
    private PostSvc postSvc;

    @POST
    @Path("newpost")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(EncapsularInfoPost entity) {
        //Gson gson = new Gson();
        //EncapsularInfoPost paquete = gson.fromJson(parameters, EncapsularInfoPost.class);
        Integer id = entity.getIdUsuario();
        Usuario userLogged = userDao.find(id);
        Post post = entity.getPost();
        
        post.setFecha(new Date());
        post.setIdPublicador(userLogged);
        postSvc.buildPost(post, userLogged);
        Map<String,String> result = new HashMap<String, String>();
        Gson gson = new Gson();
        return Response.ok().entity(gson.toJson(result)).build();
        
        
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Post entity) {
        //super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
      //  super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Post find(@PathParam("id") Integer id) {
       // return super.find(id);
       return null;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> findAll() {
        //return super.findAll();
        return null;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
