package com.mycompany.fastproductswithmaven.controladores;

import com.mycompany.fastproductswithmaven.config.conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.io.FilenameUtils;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet(name = "ModificarProducto", urlPatterns = {"/modificarProducto"})
public class ModificarProducto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombreProducto");
        String descripcion = request.getParameter("descripcion");
        int cantidadDisp = Integer.parseInt(request.getParameter("cantidadDisp"));
        String cantMinStr = request.getParameter("cantidadMin");
        int cantidadMin = (cantMinStr != null && !cantMinStr.isBlank()) ? Integer.parseInt(cantMinStr) : 0;
        String talla = request.getParameter("talla");
        double precio = Double.parseDouble(request.getParameter("precio"));

        Part filePart = request.getPart("foto");
        String nombreArchivoDB = null;

        if (filePart != null && filePart.getSize() > 0) {
            String originalName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String extension = FilenameUtils.getExtension(originalName);
            String nombreBase = FilenameUtils.getBaseName(originalName).replace(" ", "_");
            nombreArchivoDB = nombreBase + "_" + System.currentTimeMillis() + "." + extension;

            String uploadPath = "C:\\FotosFastProducts";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            
            filePart.write(uploadPath + File.separator + nombreArchivoDB);
        }

        String sql;
        if (nombreArchivoDB != null) {
            sql = "UPDATE productos SET nombre_producto=?, descripcion=?, cantidad_disponible=?, cantidad_minima=?, talla=?, precio=?, foto_url=? WHERE id_producto=?";
        } else {
            sql = "UPDATE productos SET nombre_producto=?, descripcion=?, cantidad_disponible=?, cantidad_minima=?, talla=?, precio=? WHERE id_producto=?";
        }

        try (Connection conexionDB = conexion.obtenerConexion();
             PreparedStatement sentencia = conexionDB.prepareStatement(sql)) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, descripcion);
            sentencia.setInt(3, cantidadDisp);
            sentencia.setInt(4, cantidadMin);
            sentencia.setString(5, talla);
            sentencia.setDouble(6, precio);

            if (nombreArchivoDB != null) {
                sentencia.setString(7, nombreArchivoDB);
                sentencia.setInt(8, id);
            } else {
                sentencia.setInt(7, id);
            }

            sentencia.executeUpdate();
            response.sendRedirect(request.getContextPath() + "/inventario");

        } catch (SQLException e) {
            getServletContext().log("Error al modificar producto", e);
            response.sendError(500, "Error al modificar el producto.");
        }
    }
}