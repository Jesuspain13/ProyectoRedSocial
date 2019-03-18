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
import redSocial.dao.GruposFacade;
import redSocial.dao.PostFacade;
import redSocial.dao.UsuarioFacade;
import redSocial.modelos.Amistades;
import redSocial.modelos.Grupos;
import redSocial.modelos.Usuario;
import redSocial.svc.AmistadesSvc;
import redSocial.svc.interfaces.Creatable;
import redSocial.svc.interfaces.Editable;
import redSocial.svc.interfaces.Searchable;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "PerfilUsuario", urlPatterns = {"/PerfilUsuario"})
public class PerfilUsuarioControlador extends HttpServlet {

    @EJB
    public AmistadesSvc svc;
    
    @EJB
    public PostFacade dao;
    
    @EJB
    public UsuarioFacade userDao;
    
    @EJB
    public GruposFacade groupDao;
    
    @EJB
    public Creatable creatableObj;
    
    @EJB
    public Searchable searchableObj;
    
    @EJB
    public Editable editableObj;
    
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
            if (request.getSession().getAttribute("gruposExistentes") == null) {
                //añado los grupos existentes y los usuarios existentes a la sesion
                otherGroups = groupDao.GroupList(user);
                request.getSession().setAttribute("gruposExistentes", otherGroups);
                otrosUsuarios = userDao.findOtherUsers(user);
                request.getSession().setAttribute("usuariosExistentes", otrosUsuarios);
            } else {
                otherGroups = (List<Grupos>) request.getSession().getAttribute("gruposExistentes");
                otrosUsuarios = (List<Usuario>) request.getSession().getAttribute("usuariosExistentes");
            }
            
            
            request.getSession().setAttribute("usuariosExistentes", otrosUsuarios);
            //AÑADIR AMIGO
            if (request.getParameter("nuevoAmigo") != null && !request.getParameter("nuevoAmigo").isEmpty()) {
                Amistades newRelationship = svc.nuevoAmigo(user, request.getParameter("nuevoAmigo"));
                creatableObj.create(user, newRelationship);
                editableObj.changeList(otrosUsuarios, newRelationship.getIdUsuario2(), 2);
                
                //userDao.find(user);
                
            //AÑADIR POST o COMENTARIO
            }
            if (request.getParameter("privacidad") != null &&
                    !request.getParameter("privacidad").isEmpty()) {
                //si esto no es null es que se va a crear un comentario de grupo
                int idGroup = Integer.parseInt(request.getParameter("privacidad"));
                String content = request.getParameter("contenido");
                // AÑADIR POST
                if (idGroup == 0) {
                    creatableObj.buildPost(user, content);
                //AÑADIR COMENTARIO
                } else {
                    creatableObj.buildComment(user, content, idGroup); 
                }
                //CREAR UN GRUPO Y HACERSE MIEMBRO
            }
             if (request.getParameter("nombregrupo") != null &&
                     !request.getParameter("nombregrupo").isEmpty()) {
                String groupName = request.getParameter("nombregrupo");
                creatableObj.buildGroup(user, groupName);
            } //UNIRSE A UN GRUPO EXISTENTE
             if (request.getParameter("grupoAUnirse") != null &&
                     !request.getParameter("grupoAUnirse").isEmpty()) {
                 int idGroup = Integer.parseInt(request.getParameter("grupoAUnirse"));
                 Grupos groupSelected = (Grupos) searchableObj.search(otherGroups, idGroup);
                 editableObj.changeGroup(groupSelected, user, 1);
                 //elimina el grupo de la lista de los grupos a los que no pertenece el usuario
                 editableObj.changeList(otherGroups, groupSelected, 2);
                
             } //CREAR AMISTAD CON OTRO USUARIO EXISTENTE (SIN BUSCAR)
             if (request.getParameter("otroUsuario") != null && 
                     !request.getParameter("otroUsuario").isEmpty()) {
                 int idUsuario = Integer
                         .parseInt(request.getParameter("otroUsuario"));
                 
                 Usuario newFriend = (Usuario) searchableObj
                         .search(otrosUsuarios, idUsuario);
                 
                 creatableObj.buildRelationship(user, newFriend);
                 editableObj.changeList(otrosUsuarios, newFriend, 2);
             }
//            
//            List<Grupos> otrosGrupos = groupDao.GroupList(user);
//            request.setAttribute("gruposExistentes", otrosGrupos);
//            List<Usuario> otrosUsuarios = userDao.findOtherUsers(user);
//            request.setAttribute("usuariosExistentes", otrosUsuarios);
            
            
//            List<Post> res = svc.buscarPostDeAmigos(user);
//            request.setAttribute("PostAmigos", res);
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
