/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lntormin.javaee.web.servlets;

import com.lntormin.javaee.ejb.beans.UserBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lntormin
 */
public class LoginServlet extends HttpServlet {

    private static final long SERIALVERSIONUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userRequest = request.getParameter("login");
        String passwordRequest = request.getParameter("password");

        System.out.println("Context PATH: " + getServletContext().getContextPath());
        Context context;
        UserBeanRemote beanRemote = null;
        
        try{
            context = new InitialContext();
            beanRemote = (UserBeanRemote) context.lookup("java:global/EnterpriseApp/EJBModule-ejb/UserBean");
        }catch(NamingException namingException){
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE,null, namingException);
        }
        
        if(beanRemote.authenticate(userRequest, passwordRequest)){
            HttpSession session = request.getSession();
            session.setAttribute("user", userRequest);
            //session expires in 30 minutes
            session.setMaxInactiveInterval(30*60);
            Cookie userName = new Cookie("user", userRequest);
            userName.setMaxAge(30*60);
            response.addCookie(userName);
            response.sendRedirect("WebApp/restricted/index.jsp");
        }else{
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color='red'>User or password are incorrect.</font>");
            dispatcher.include(request, response);
        }

    }

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

}
