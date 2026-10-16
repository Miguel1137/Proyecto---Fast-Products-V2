/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.fastproductswithmaven.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 *
 * @author miguel
 */
@WebServlet(name = "LogOutServlet", urlPatterns = {"/LogOutServlet"})
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Buscamos la sesión actual sin crear una nueva (pasando 'false')
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // 2. Destruimos por completo la sesión y borramos todos sus datos
            session.invalidate();
        }
        
        // 3. Redirigimos al usuario de vuelta al formulario de inicio de sesión
        response.sendRedirect("index.jsp");
    }
}
