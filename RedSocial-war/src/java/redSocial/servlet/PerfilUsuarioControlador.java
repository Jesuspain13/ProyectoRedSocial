/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.entities.UsuarioSvc;
import redSocial.svc.interfaces.entities.ComentarioSvc;
import redSocial.svc.interfaces.entities.GruposSvc;
import svc.manejoHttpRequest.ComentarioHttp;
import svc.manejoHttpRequest.GrupoHttp;
import svc.manejoHttpRequest.PostHttp;
import svc.manejoHttpRequest.UsuarioHttp;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "PerfilUsuario", urlPatterns = {"/PerfilUsuario"})
public class PerfilUsuarioControlador extends HttpServlet {

    @EJB
    public UsuarioSvc userSvc;

    @EJB
    public PostHttp postSvc;

    @EJB
    public UsuarioHttp svc;

    @EJB
    public ComentarioSvc commentSvc;

    @EJB
    public GrupoHttp groupHttp;

    @EJB
    public ComentarioHttp commentHttp;

    @EJB
    public GruposSvc groupSvc;

    private final static String SUCCESS = "/nuevoPerfil.jsp";

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
            List<Grupos> otherGroups;
            List<Usuario> otrosUsuarios;
//
//            //añado los grupos existentes y los usuarios existentes a la sesion si no están
//            otherGroups = groupHttp.encontrarOtrosGrupos(request);
//            request.setAttribute("gruposExistentes", otherGroups);
//            otrosUsuarios = svc.encontrarOtrosUsuarios(request);
//            request.setAttribute("usuariosExistentes", otrosUsuarios);

            //AÑADIR AMIGO
            if (request.getParameter("newFriendEmail") != null && !request.getParameter("newFriendEmail").isEmpty()) {
                svc.nuevoAmigoPorEmail(request);
                request.getSession().removeAttribute("usuario");

            //AÑADIR POST o COMENTARIO
            }
            if (request.getParameter("privacidad") != null
                    && !request.getParameter("privacidad").isEmpty()) {
                //si esto no es null es que se va a crear un comentario de grupo
                int idGroup = Integer.parseInt(request.getParameter("privacidad"));
                // AÑADIR POST
                if (idGroup == 0) {
                    postSvc.crearPost(request);
                    request.getSession().removeAttribute("usuario");
                    //AÑADIR COMENTARIO
                } else {
                    user = commentHttp.construirComentario(request);
                    request.getSession().removeAttribute("usuario");
                }
                //CREAR UN GRUPO Y HACERSE MIEMBRO
            }
            if (request.getParameter("groupNameToCreate") != null
                    && !request.getParameter("groupNameToCreate").isEmpty()) {
                user = groupHttp.construirGrupo(request);
                request.getSession().removeAttribute("usuario");
            } //UNIRSE A UN GRUPO EXISTENTE
            if (request.getParameter("idGroupToFollow") != null
                    && !request.getParameter("idGroupToFollow").isEmpty()) {
                user = groupHttp.unirseAGrupo(request);
                request.getSession().removeAttribute("usuario");

            } //CREAR AMISTAD CON OTRO USUARIO EXISTENTE (SIN BUSCAR)
            if (request.getParameter("idNewFriend") != null
                    && !request.getParameter("idNewFriend").isEmpty()) {

                svc.nuevoAmigoYaExistente(request);
                request.getSession().removeAttribute("usuario");
            }

            request.getSession().setAttribute("usuario", user);
            //añado los grupos existentes y los usuarios existentes a la sesion si no están
            otherGroups = groupHttp.encontrarOtrosGrupos(request);
            request.setAttribute("gruposExistentes", otherGroups);
            otrosUsuarios = svc.encontrarOtrosUsuarios(request);
            request.setAttribute("usuariosExistentes", otrosUsuarios);
            request.getRequestDispatcher(SUCCESS).forward(request, response);
        } catch (Exception ex) {
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
