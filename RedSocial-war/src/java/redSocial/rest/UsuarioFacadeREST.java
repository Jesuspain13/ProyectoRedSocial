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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import redSocial.dao.UsuarioFacadeLocal;
import redSocial.modelos.EncapsularInfoPost;
import redSocial.modelos.Grupos;
import redSocial.modelos.Sugerencias;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.entities.GruposSvc;
import redSocial.svc.interfaces.entities.UsuarioSvc;

/**
 *
 * @author Jesus
 */
@javax.enterprise.context.RequestScoped
@Path("redsocial")
public class UsuarioFacadeREST {
    
    private Gson gson;
    private Usuario userLogged;

    
    @Inject
    private UsuarioSvc userSvc;
    
    @Inject
    private GruposSvc groupSvc;
    
    //Error al usar EJB!!!! pregunta!!
    //El json que me devuelve no tiene los atributos que muestran las listas indirectas
    @Inject
    private UsuarioFacadeLocal userDao;

    @POST
    @Path("/login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response doLogin(Usuario entity) {
        //Usuario user = userSvc.loginByEmailAndPass("jespana@uma.es", "22222");
        
        Usuario user = userSvc.loginByEmailAndPass(entity.getEmail(), entity.getPassword());
        String userJson = Usuario.toJson(user);
        GenericEntity entityGenerated = 
                        new GenericEntity(userJson, String.class);
        return Response.ok().entity(entityGenerated).build();
    }
    
    @POST
    @Path("/getsuggested")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendSuggestion(Usuario entity) {
        Usuario user = userDao.find(entity.getId());
        //busca al usuario por el id mandado
        if (user != null) {   
            //limitamos a 5 las sugerencias
            List<Usuario> userSuggested = getOnlyFiveItems(userSvc.findOtherUsers(user));
            List<Grupos> groupSuggested = getOnlyFiveItems(groupSvc.findOtherGroups(user));
            //encapsula las sugerencias en una clase
            Sugerencias sug = new Sugerencias();
            //elimina las posibles concurrencias y convierte a json
            String json = sug.suggestionToJson(userSuggested, groupSuggested);
            GenericEntity entityGenerated =
                    new GenericEntity(json, String.class);
            return Response.ok().entity(entityGenerated).build();
        } else {
            return Response.status(404).entity("NO ESTAS REGISTRADO").build();
        }
    }
    
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(Usuario entity) {
        try {
            Usuario userCreated = userSvc.buildUser(entity);
            String userJson = Usuario.toJson(userCreated);
            GenericEntity entityGenerated = 
                        new GenericEntity(userJson, String.class);
            return Response.ok().entity(entityGenerated).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/refreshuser")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response refreshUser(Usuario entity) {
        Usuario userLogged = userDao.find(entity.getId());
        String userJson = Usuario.toJson(userLogged);
        GenericEntity entityGenerated = 
                        new GenericEntity(userJson, String.class);
        return Response.ok().entity(entityGenerated).build();
        ////o no se retorna el usuario o si se retorna el suusario actualizado
        
    }

    @POST
    @Path("/deletefriend")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeFriend(EncapsularInfoPost entity) {
        Usuario user = userDao.find(entity.getIdUsuario());
        userSvc.delete(user, entity.getIdAmigo());
        Map<String,String> result = new HashMap<String, String>();
        Gson gson = new Gson();
        return Response.ok().entity(gson.toJson(result)).build();
            
    }

    @POST
    @Path("/addfriend")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response addFriendById(EncapsularInfoPost entity) {
        Usuario userLogged = userDao.find(entity.getIdUsuario());
        userSvc.newFriendById(userLogged, entity.getIdAmigo());
        Map<String,String> result = new HashMap<String, String>();
        Gson gson = new Gson();
        gson.toJson(result);
        return Response.ok().entity(gson.toJson(result)).build();
        
    }

    @POST
    @Path("/addfriendemail")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response addFriendByEmail(EncapsularInfoPost entity) {
        Usuario userLogged = userDao.find(entity.getIdUsuario());
        userSvc.newFriendByEmail(userLogged, entity.getEmailAmigo());
        Map<String,String> result = new HashMap<String, String>();
        Gson gson = new Gson();
        return Response.ok().entity(gson.toJson(result)).build();
        
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
    
    private List getOnlyFiveItems(List listOfAllItems) {
        List result = new ArrayList();
        boolean terminado = false;
        while (!terminado) {
            int tamaño = listOfAllItems.size();
            int posicionAleatoria = (int) (Math.random())*(tamaño);
            if (!result.contains(listOfAllItems.get(posicionAleatoria))){
                result.add(listOfAllItems.get(posicionAleatoria)); 
                listOfAllItems.remove(listOfAllItems.get(posicionAleatoria));
            }    
            if (result.size() >= 5 || listOfAllItems.size() == 0) {
                terminado = true;
            }
            
        }
        return result;
    }
    
    
}
