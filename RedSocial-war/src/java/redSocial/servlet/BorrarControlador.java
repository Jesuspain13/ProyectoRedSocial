/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import redSocial.dao.AmistadesFacade;
import redSocial.dao.UsuarioFacade;
import redSocial.modelos.Amistades;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.Deletable;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "BorrarControlador", urlPatterns = {"/BorrarControlador"})
public class BorrarControlador extends HttpServlet {

    
    @EJB
    private UsuarioFacade dao;
    
    @EJB
    private AmistadesFacade amistadesDao;
    
    @EJB 
    private Deletable deletableObj; 
    

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
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            if (request.getParameter("amigoABorrar") != null) {
                int idAmistadABorrar = Integer
                        .parseInt(request.getParameter("amigoABorrar"));
                
                

//                Amistades amistadABorrar = BorrarControlador
//                        .encontrarAmistad(usuario, idAmistadABorrar);
                deletableObj.delete(usuario, idAmistadABorrar, 1);
                
//                amistadesDao.remove(amistadABorrar);
//                BorrarControlador.actualizarAmigosBorrados(usuario, amistadABorrar);
            } else if (request.getParameter("borrarPost") != null) {
                int idPostABorrar = Integer
                        .parseInt(request.getParameter("borrarPost"));
                deletableObj.delete(usuario, idPostABorrar, 2);
            }
            
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

    private static void actualizarAmigosBorrados(Usuario user, Amistades amistadABorrar) {
        List<Amistades> amistadesAntiguas = user.getAmistadesList();
        amistadesAntiguas.remove(amistadABorrar);
        user.setAmistadesList(amistadesAntiguas);
    }
    
    private static Amistades encontrarAmistad(Usuario user, int idAmistad) {
        
        Iterator amistades = user.getAmistadesList().iterator();
        Amistades res = null;
        boolean encontrado = false;
        Amistades iteradorAmistad;
        while(amistades.hasNext() && !encontrado) {
            iteradorAmistad = (Amistades) amistades.next();
            if (iteradorAmistad.getAmistadesid() == idAmistad) {
                res = iteradorAmistad;
                encontrado = true;
                
            }
        }
        return res;
    }
}
