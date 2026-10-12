package com.mycompany.fastproductswithmaven.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuarioIngresado = request.getParameter("user");
        String claveIngresada = request.getParameter("password");
        
        // Validación estática de prueba
        if ("admin".equals(usuarioIngresado) && "1111".equals(claveIngresada)) {
            // Credenciales correctas: Creamos sesión y redirigimos
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuarioIngresado);
            response.sendRedirect("inventario.html");
            
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
        }
    }
}
