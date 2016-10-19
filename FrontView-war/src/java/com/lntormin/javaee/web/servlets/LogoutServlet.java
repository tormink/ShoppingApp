/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lntormin.javaee.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
public class LogoutServlet extends HttpServlet {
    private static final long SERIALVERSIONUID = 1L;
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
                response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("JSESSIONID")) {
                    System.out.println("JSESSIONID=" + c.getValue());
                    break;
                }
            }
        }
        //invalidate session if it exists
        HttpSession session = request.getSession(false);
        System.out.println("User= " + session.getAttribute("user"));
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.html");
    }


}
