package com.mycompany.fastproductswithmaven.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(name = "MostrarImagen", urlPatterns = {"/mostrarImagen"})
public class MostrarImagen extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombreFoto = request.getParameter("foto");
        
        if (nombreFoto != null && !nombreFoto.isEmpty()) {
            File archivoFoto = new File("C:\\FotosFastProducts", nombreFoto);
            
            if (archivoFoto.exists()) {
                response.setContentType(getServletContext().getMimeType(archivoFoto.getName()));
                Files.copy(archivoFoto.toPath(), response.getOutputStream());
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}