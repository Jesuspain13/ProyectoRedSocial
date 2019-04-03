/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import redSocial.dao.UsuarioFacadeLocal;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.entities.ComentarioSvc;
import redSocial.svc.interfaces.entities.GruposSvc;
import redSocial.svc.interfaces.entities.PostSvc;
import redSocial.svc.interfaces.entities.UsuarioSvc;
import svc.manejoHttpRequest.ComentarioHttp;
import svc.manejoHttpRequest.GrupoHttp;
import svc.manejoHttpRequest.PostHttp;
import svc.manejoHttpRequest.UsuarioHttp;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "BorrarControlador", urlPatterns = {"/BorrarControlador"})
public class BorrarControlador extends HttpServlet {

    
    @EJB
    private UsuarioFacadeLocal dao;
    
    
    @EJB
    private UsuarioSvc userSvc;
    
    @EJB
    private PostSvc postSvc;
    
    @EJB
    private ComentarioSvc commentSvc;
    
    @EJB
    private GruposSvc groupSvc;
    
    @EJB
    private GrupoHttp groupHttp;
    
    @EJB
    private UsuarioHttp userHttp;
    
    @EJB
    private PostHttp postHttp;
    
    @EJB
    private ComentarioHttp commentHttp;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Usuario user = (Usuario) request.getSession().getAttribute("usuario");
            //dejar de seguir a un amigo
            if (request.getParameter("UnfollowEverybody") != null) {
                user = userHttp.borrarTodo(request);
            }
            if (request.getParameter("friendToUnfollow") != null) {
                user = (Usuario) userHttp.dejarDeSeguir(request);
//               user = (Usuario) userSvc.delete(request);
//                int idAmistadABorrar = Integer
//                        .parseInt(request.getParameter("friendToUnfllow"));
//                
//                deletableObj.delete(user, idAmistadABorrar, 1);
                
            } // borrar un post nuestro
            else if (request.getParameter("postToDelete") != null) {
                user = (Usuario) postHttp.borrarPost(request);
//                user = (Usuario) postSvc.delete(request);
//                int idPostABorrar = Integer
//                        .parseInt(request.getParameter("postToDelete"));
//                
//                deletableObj.delete(user, idPostABorrar, 2);
                
            } // borrar un comentario nuestro de un grupo en el que estamos
            if (request.getParameter("commentToDelete") != null && 
                    !request.getParameter("commentToDelete").isEmpty()) {
                user = (Usuario) commentHttp.borrarComentario(request);
//                user = (Usuario) commentSvc.delete(request);
//                int idCommentToDelete = Integer
//                        .parseInt(request.getParameter("comentarioABorrar"));
//                deletableObj.delete(user, idCommentToDelete, 4);
            } // dejar de seguir a un grupo
            if (request.getParameter("idGroupToUnfollow") != null && 
                    !request.getParameter("idGroupToUnfollow").isEmpty()) {
                
                groupHttp.salirDeGrupo(request);
                //groupSvc.unfollowGroup(request);
//                int idGroupToUnfollow = Integer
//                        .parseInt(request.getParameter("idGrupoDejarSeguir"));
//                deletableObj.delete(user, idGroupToUnfollow, 3);
            }

            request.getSession().removeAttribute("usuario");
            request.getSession().setAttribute("usuario", user);
            request.getRequestDispatcher("PerfilUsuario").forward(request, response);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

   
}
