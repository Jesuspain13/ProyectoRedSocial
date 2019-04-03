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
import javax.servlet.http.HttpSession;
import redSocial.dao.UsuarioFacadeLocal;
import redSocial.modelos.Usuario;
import svc.manejoHttpRequest.UsuarioHttp;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class LoginControlador extends HttpServlet {

    private static final String SUCCESS = "/PerfilUsuario";
    
    @EJB
    private UsuarioFacadeLocal dao;
    
    @EJB
    private UsuarioHttp userHttp;

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
//            String email = request.getParameter("email");
//            String password = request.getParameter("password");
            Usuario res = userHttp.logearse(request);
            
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", res);
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
